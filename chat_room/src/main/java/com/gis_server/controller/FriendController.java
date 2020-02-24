package com.gis_server.controller;

import com.gis_server.pojo.Friend;
import com.gis_server.pojo.Message;
import com.gis_server.service.FriendService;
import com.gis_server.utils.MsgUtils;
import com.gis_server.utils.ResponseInformation;
import com.gis_server.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class FriendController {

    Logger logger = LoggerFactory.getLogger(FriendController.class);

    private static final int RESPONSECODE_AGREE = 1;
    private static final int RESPONSECODE_REFUSE = 0;

    @Autowired
    FriendService friendService;

    @ResponseBody
    @PostMapping("/responseAddFriendServlet")
    public String responseAddFriend(@RequestBody Map<String, String> map){
        logger.info("=========== 添加好友 =================");
        if(map.get("responseCode") != null &&Integer.parseInt(map.get("responseCode")) == RESPONSECODE_AGREE){
            logger.info("接受");
            Friend friend = new Friend();
            friend.setfFirendid(map.get("from"));
            friend.setfUserid(map.get("to"));
            if(friendService.addFriendRelationship(friend) > 0){
                Message hintMsg = new Message();
                hintMsg.setmFromuserid("");
                hintMsg.setmTouserid(map.get("to"));
                hintMsg.setmMessagestypeid(Message.MESSAGE_TYPE_SYSTEMNOTIFY);
                hintMsg.setmTime(new Timestamp(new Date().getTime()));
                hintMsg.setmPostmessages("ID:" + map.get("from") + " 接受了你的好友请求，你们已成为好友啦");
                sendMsgToRequester(hintMsg);
                return ResponseInformation.getSuccessInformation();
            } else {
                return ResponseInformation.getErrorInformation("添加好与关系失败");
            }
        } else if(Integer.parseInt(map.get("responseCode")) == RESPONSECODE_REFUSE){  // 拒绝
            logger.info("拒绝");

            Message hintMsg = new Message();
            hintMsg.setmFromuserid("");
            hintMsg.setmTouserid(map.get("to"));
            hintMsg.setmMessagestypeid(Message.MESSAGE_TYPE_SYSTEMNOTIFY);
            hintMsg.setmTime(new Timestamp(new Date().getTime()));
            hintMsg.setmPostmessages("ID:" + map.get("from") + " 拒绝了你的好友请求");
            sendMsgToRequester(hintMsg);

            return ResponseInformation.getSuccessInformation();
        }
        return ResponseInformation.getErrorInformation("添加好与关系失败");
    }


    /**
     * 通过 websocket 通知请求者添加好友请求被接收或拒绝
     */
    private void sendMsgToRequester(Message msg){
        List<WebSocketServer> connectedUsers = WebSocketServer.getConnectedUsers();
        for (WebSocketServer chatController : connectedUsers){
            if (msg.getmTouserid().equals(chatController.getUser().getuLoginid())){
                try {
                    chatController.sendMessageText(MsgUtils.msgToJsonStr(msg));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
