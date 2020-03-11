package com.gis_server.iot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gis_server.pojo.Device;
import com.gis_server.pojo.TempHum;
import com.gis_server.service.DeviceService;
import com.gis_server.service.TempHumService;
import com.gis_server.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IotServerHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = LoggerFactory.getLogger(IotServerHandler.class);

//    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static Map<Integer, Channel> channelMap = new ConcurrentHashMap<>();

    private DeviceService deviceService = (DeviceService) SpringUtil.getBean("deviceService");

    private TempHumService tempHumService = (TempHumService) SpringUtil.getBean("tempHumService");

    public static final int RECV_DEVICE_BASIC_INFO = 1;
    public static final int RECV_TEMP_HUM_MSG = 2;

    /**
     * 每当有新客户端接入的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
//        }
//        channels.add(ctx.channel());
    }

    /**
     * 客户端断开时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
//        }
//        channels.remove(ctx.channel());
    }

    /**
     * 接受到客户端消息时触发
     * @param ctx
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel incoming = ctx.channel();
        logger.info("IotServerReceive: " + s);
        logger.info("当前线程："+ Thread.currentThread().getName());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(s);
        if(jsonNode.has("type") && Integer.valueOf(jsonNode.get("type").toString()) == IotServerHandler.RECV_DEVICE_BASIC_INFO){
            if(jsonNode.has("data")){
                Device device = deviceService.selectById(jsonNode.findValue("id").asInt());
                if(device != null){
                    channelMap.put(device.getId(), incoming);
                    Device recvDevice = objectMapper.readValue(jsonNode.get("data").toString(), Device.class);
                    recvDevice.setId(device.getId());
                    deviceService.updateDevice(recvDevice);
                }else{
//                    incoming.writeAndFlush("非法设备接入,请先在后台管理中添加此设备的信息后再尝试连接服务...");
                    incoming.writeAndFlush("exit"); // 发送 exit 标记表示客户端断开连接
//                    incoming.close();  // 关闭后客户端接收到异常消息无限循环
                }
            }else{
                logger.info("接收到的消息格式错误");
                incoming.writeAndFlush("接收到的数据格式错误");
            }
        } else if(jsonNode.has("type") && Integer.valueOf(jsonNode.get("type").toString()) == IotServerHandler.RECV_TEMP_HUM_MSG) {
            TempHum tempHum = objectMapper.readValue(jsonNode.get("data").toString(), TempHum.class);
            tempHumService.insertTempHum(tempHum);
        }
    }

    /**
     * 在建立连接时 channel 被启用时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        logger.info("IotDevice:"+incoming.remoteAddress()+"连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        logger.info("IotDevice:"+incoming.remoteAddress()+"掉线");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        logger.info("IotDevice:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
