package com.gis_server.websocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gis_server.config.HttpSessionConfig;
import com.gis_server.pojo.Message;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.MsgService;
import com.gis_server.service.UserService;
import com.gis_server.service.impl.RedisService;
import com.gis_server.utils.DateUtils;
import com.gis_server.utils.MsgUtils;
import com.gis_server.utils.SpringUtil;
import com.gis_server.utils.Utils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@ServerEndpoint(value="/chat/{loginUserID}",configurator= HttpSessionConfig.class)
public class WebSocketServer {

    Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    // 定义一个socket 连接列表，每个用户登录都会创建连接并保存到此列表中
    private static List<WebSocketServer> connectedUsers = new CopyOnWriteArrayList<>();
    private Session session;
    private HttpSession httpSession;
    private SysUser user;
    private ObjectMapper mapper = new ObjectMapper();
    private static String context_path = "/chat"; // 和配置文件中 context-path 相同

    private UserService userService = (UserService) SpringUtil.getBean("userService");

    private MsgService msgService = (MsgService) SpringUtil.getBean("msgService");

    private  RedisService redisService = (RedisService) SpringUtil.getBean("redisService");

    public static List<WebSocketServer> getConnectedUsers() {
        return connectedUsers;
    }

    public SysUser getUser() {
        return user;
    }

    /**
     * 当连接创建的时候
     * @param session
     * @param loginUserID
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam(value="loginUserID") String loginUserID) {
        System.out.println("========== 有新的客户端连接 ==============");
        this.session = session;
        this.httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        int maxSize = 400 * 1024; // 设置接收缓存区的大小
        session.setMaxTextMessageBufferSize(maxSize);

        this.user = userService.queryUserByLoginID(loginUserID);  // 从数据库中查询此用户
        if(user == null){
//            sendNotifyMessage("errorInfo");  // 给用户提示错误信息
        }

        connectedUsers.add(this); // 将连接“句柄”添加到列表中

        logger.info("ID:" + user.getuLoginid() + "上线！");

//        RedisService redisService = new RedisService();
        List<String> offlineMsg = redisService.getOfflineMsg(loginUserID);
        for (String msg_json : offlineMsg){
            try {
                sendMessageText(msg_json);
                Message msg = MsgUtils.jsonStrToMsg(msg_json);
                msg.setmStatus(Utils.byteToBoolean(Message.MESSAGE_STATUS_RECEIVED));
                if(msg.getmMessagestypeid() == Message.MESSAGE_TYPE_ORDINARY){
                    msgService.insertMsgToDB(msg); // 将消息保存到消息记录中
                }

            } catch (IOException e) {
                e.printStackTrace();
                logger.info("发送离线消息给 " + loginUserID + "失败");
            }
        }
        if (redisService.removeOffLineMsg(loginUserID)){
            logger.info("成功将所有离线消息都发送给 " + loginUserID +" 并清空了离线消息队列");
        }

    }

    /**
     * 当连接关闭时
     */
    @OnClose
    public void onClose(){
        connectedUsers.remove(this); // 从连接列表中删除此连接
//		sendNotifyMessage();
//		System.out.println("ID:" +user.getULoginId() + "下线！");
        logger.info("ID:" +user.getuLoginid() + "下线！");
        // 下线后，应该发送通知
    }


    /**
     * 接收到的消息，主要处理通知的部分
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session){
        logger.info(message);
        try {
            JSONObject jsonObject = new JSONObject(message);
            Message msg = new Message();
            msg.setmPostmessages(jsonObject.getString("messageContent"));
            msg.setmMessagestypeid(jsonObject.getInt("messageType"));
            msg.setmStatus(Utils.byteToBoolean((byte) jsonObject.getInt("status")));
            msg.setmFromuserid(jsonObject.getString("from"));
            msg.setmTouserid(jsonObject.getString("to"));
            msg.setmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonObject.getString("time")));

            if(msg.getmFromuserid() == null){  // 如果对应的发送者域非空，说明为有效消息
                logger.info("发送者为空！");
                return;
            }
            logger.info("消息的 json 串：" + msg.toString());

            if(msg.getmTouserid() != null){  // 说明有接收者，为有效消息
                if(msg.getmMessagestypeid() == Message.MESSAGE_TYPE_ORDINARY){  // 如果消息类型为普通消息
                    if(msg.getmTouserid().equals("chatRoom")){
                        sendToAllMsg(msg);
                    }
                    else{
                        sendNotifyMessage(msg);
                    }
                    msg.setmStatus(Utils.byteToBoolean(Message.MESSAGE_STATUS_RECEIVED));  // 已发送出去的的消息默认对方已收到
                    msgService.insertMsgToDB(msg); // 将消息添加到消息记录中
                }
                else if(msg.getmMessagestypeid() == Message.MESSAGE_TYPE_ADDFRIEND){ // 如果为添加好友
                    SysUser toUser = userService.queryUserByLoginID(msg.getmTouserid());
                    if (toUser != null){
                        SysUser fromUser = userService.queryUserByLoginID(msg.getmFromuserid());
                        msg.setmFromUserHeadPortrait(fromUser.getuHeadportrait());
                        msg.setmFromUserNickName(fromUser.getuNickname());
                        logger.info(mapper.writeValueAsString(msg));
                        sendNotifyMessage(msg);
                    }
                    else{
                        Message hintMsg = new Message();
                        hintMsg.setmFromuserid("");
                        hintMsg.setmTouserid(msg.getmFromuserid());
                        hintMsg.setmStatus(Utils.byteToBoolean(Message.MESSAGE_STATUS_UNRECEIVED));
                        hintMsg.setmMessagestypeid(Message.MESSAGE_TYPE_SYSTEMNOTIFY);
                        hintMsg.setmTime(new Timestamp(new Date().getTime()));
                        hintMsg.setmPostmessages("此用户不存在");
                        sendNotifyMessage(hintMsg);
                    }
                }
                else if(msg.getmMessagestypeid() == Message.MESSAGE_TYPE_IMAGE){  // 如果消息类型为图片类型
                    logger.info("接收到图片消息" + msg.getmPostmessages());
                    String filePath = DecodeBase64ToImg(msg.getmPostmessages());
                    if(filePath != null){
                        msg.setmPostmessages(filePath);
                        if(msg.getmTouserid().equals("chatRoom")){  // 如果是聊天室消息
                            sendToAllMsg(msg);
                        }
                        else{
                            sendNotifyMessage(msg);
                        }
                        msg.setmStatus(Utils.byteToBoolean(Message.MESSAGE_STATUS_RECEIVED));  // 已发送出去的的消息默认对方已收到
                        msgService.insertMsgToDB(msg); // 将消息添加到消息记录中
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于解析接收到的非普通文本内容
     * 接收到的消息内容格式 {data:image/jpeg;base64,*******（base64 编码后的文件内容）}
     * @param imgStr
     * @return 返回文件保存路径
     */
    public String DecodeBase64ToImg(String imgStr){
        String fileType = "";  // 文件类型 （图片，文本...）
        String fileFormat = ""; // 文件格式 (jpg, jpeg, png...)
        Pattern pattern = Pattern.compile("(?<=data:).*(?=;)");
        Matcher matcher = pattern.matcher(imgStr);
        if(matcher.find()){
            fileType = matcher.group().split("/")[0];
            fileFormat = matcher.group().split("/")[1];
        }
        BASE64Decoder d = new BASE64Decoder();
        try {
            byte[] bytes = d.decodeBuffer(imgStr.split(",")[1]);  // 文件中数据

            if ("image".equals(fileType)){
                StringBuilder filePath = new StringBuilder();
                String rootPath = ResourceUtils.getURL("classpath:").getPath();
                System.out.println("rootPath=" + rootPath);
                filePath.append(rootPath)
                        .append("static/")
                        .append("users/")
                        .append(this.user.getuLoginid())
                        .append("/msg_img/")
                        .append(DateUtils.getNow("yyyyMMddHHmmss"))
                        .append(".")
                        .append(fileFormat);
                File file = new File(filePath.toString());
                File fileDir = file.getParentFile();
                if(!fileDir.exists()){
                    fileDir.mkdirs();  // 表示如果当前目录不存在就创建，包括所有必须的父目录
                }
                if(!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fs = new FileOutputStream(file, true);
                fs.write(bytes);
                fs.flush();
                fs.close();
                logger.info("接收到图片消息，已保存到：" + filePath);

                Pattern pattern2 = Pattern.compile("(?=/users).*");
                Matcher matcher2 = pattern2.matcher(filePath);
                if(matcher2.find()){
                    System.out.println("==========" + context_path + matcher2.group());
                    return context_path + matcher2.group();
                }
            }

            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送消息给 msg 中 to 用户，如果 to 不在线，则保存到其消息队列中
     * @param msg
     * @throws IOException
     */
    public void sendNotifyMessage(Message msg) throws IOException {
        boolean toUserOnline = false;
        for (WebSocketServer chatController : connectedUsers){
            if (chatController.getUser().getuLoginid().trim().equals(msg.getmTouserid())){  // 发送给 to
                toUserOnline = true;
                chatController.sendMessageText(MsgUtils.msgToJsonStr(msg));
            }
        }
        if(!toUserOnline){ // 说明 接收者 不在线
            // 将离线消息写到 redis 中的离线消息队列中
            redisService.insertOfflineMsg(msg.getmTouserid(), MsgUtils.msgToJsonStr(msg));
        }
    }

    public void sendToAllMsg(Message msg) throws IOException {
        for (WebSocketServer chatController : connectedUsers){
            if(!chatController.getUser().getuLoginid().equals(this.user.getuLoginid())){
                chatController.sendMessageText(MsgUtils.msgToJsonStr(msg));
            }
        }
    }

    public void sendMessageText(String content) throws IOException {
        this.session.getBasicRemote().sendText(content);
    }


    public void sendBinary(ByteBuffer byteBuffer) throws IOException {
        this.session.getBasicRemote().sendBinary(byteBuffer);
    }


    @OnError
    public void onError(Throwable throwalble) {
        logger.info("========= error ==========");
        throwalble.printStackTrace();
    }


}
