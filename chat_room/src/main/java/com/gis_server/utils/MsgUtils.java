package com.gis_server.utils;

import com.gis_server.pojo.Message;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class MsgUtils {

    // 定义 Json key 常量
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String MESSSAGE_CONTENT = "messageContent";
    public static final String MESSAGE_TYPE = "messageType";
    public static final String SEND_TIME = "time";
    public static final String MESSAGE_STATUS = "status";
    public static final String FROM_USER_HEADPORTRAIT = "fromUserHeadPortrait";
    public static final String FROM_USER_NICKNAME = "fromUserNickName";

    public static String msgToJsonStr(Message msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FROM, msg.getmFromuserid());
        jsonObject.put(TO, msg.getmTouserid());
        jsonObject.put(MESSAGE_TYPE, msg.getmMessagestypeid());
        jsonObject.put(MESSSAGE_CONTENT, msg.getmPostmessages());
        jsonObject.put(SEND_TIME, msg.getmTime());
        jsonObject.put(MESSAGE_STATUS, msg.getmStatus());
        jsonObject.put(FROM_USER_HEADPORTRAIT, msg.getmFromUserHeadPortrait());
        jsonObject.put(FROM_USER_NICKNAME, msg.getmFromUserNickName());
        return jsonObject.toString();
    }

    public static JSONObject msgToJson(Message msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FROM, msg.getmFromuserid());
        jsonObject.put(TO, msg.getmTouserid());
        jsonObject.put(MESSAGE_TYPE, msg.getmMessagestypeid());
        jsonObject.put(MESSSAGE_CONTENT, msg.getmPostmessages());
        jsonObject.put(SEND_TIME, msg.getmTime());
        jsonObject.put(MESSAGE_STATUS, msg.getmStatus());
        jsonObject.put(FROM_USER_HEADPORTRAIT, msg.getmFromUserHeadPortrait());
        jsonObject.put(FROM_USER_NICKNAME, msg.getmFromUserNickName());
        return jsonObject;
    }

    public static Message jsonStrToMsg(String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        Message msg = new Message();
        if (jsonObject.has(FROM)) {
            msg.setmFromuserid(jsonObject.getString(FROM));
        }
        if (jsonObject.has(TO)) {
            msg.setmTouserid(jsonObject.getString(TO));
        }
        if (jsonObject.has(MESSSAGE_CONTENT)) {
            msg.setmPostmessages(jsonObject.getString(MESSSAGE_CONTENT));
        }
        if (jsonObject.has(MESSAGE_TYPE)) {
            msg.setmMessagestypeid(jsonObject.getInt(MESSAGE_TYPE));
        }
        if (jsonObject.has(SEND_TIME)) {
            msg.setmTime(new Date(jsonObject.getString(SEND_TIME)));
        }
        if (jsonObject.has(MESSAGE_STATUS)) {
            msg.setmStatus(jsonObject.getBoolean(MESSAGE_STATUS));
        }
        if(jsonObject.has(FROM_USER_HEADPORTRAIT)){
            msg.setmFromUserHeadPortrait(jsonObject.getString(FROM_USER_HEADPORTRAIT));
        }
        if(jsonObject.has(FROM_USER_NICKNAME)){
            msg.setmFromUserNickName(jsonObject.getString(FROM_USER_NICKNAME));
        }
        return msg;
    }

}
