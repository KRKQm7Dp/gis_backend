package com.gis_server.pojo;

import java.util.Date;

public class Message {

    // 定义消息类型常量
    public static final int MESSAGE_TYPE_ORDINARY = 0;
    public static final int MESSAGE_TYPE_IMAGE = 1;
    public static final int MESSAGE_TYPE_SYSTEMNOTIFY = 2;
    public static final int MESSAGE_TYPE_ADDFRIEND = 3;

    // 定义消息状态常量
    public static final byte MESSAGE_STATUS_RECEIVED = 1;
    public static final byte MESSAGE_STATUS_UNRECEIVED = 0;

    // 定义 Json key 常量
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String MESSSAGE_CONTENT = "messageContent";
    public static final String MESSAGE_TYPE = "messageType";
    public static final String SEND_TIME = "time";
    public static final String MESSAGE_STATUS = "status";
    public static final String FROM_USER_HEADPORTRAIT = "fromUserHeadPortrait";
    public static final String FROM_USER_NICKNAME = "fromUserNickName";

    private Integer mId;

    private Boolean mStatus;

    private Date mTime;

    private Integer mMessagestypeid;

    private String mFromuserid;

    private String mTouserid;

    private String mPostmessages;

    private String mFromUserHeadPortrait;

    private String mFromUserNickName;

    public String getmFromUserHeadPortrait() {
        return mFromUserHeadPortrait;
    }

    public void setmFromUserHeadPortrait(String mFromUserHeadPortrait) {
        this.mFromUserHeadPortrait = mFromUserHeadPortrait;
    }

    public String getmFromUserNickName() {
        return mFromUserNickName;
    }

    public void setmFromUserNickName(String mFromUserNickName) {
        this.mFromUserNickName = mFromUserNickName;
    }

    public Message(Integer mId, Boolean mStatus, Date mTime, Integer mMessagestypeid, String mFromuserid, String mTouserid, String mPostmessages) {
        this.mId = mId;
        this.mStatus = mStatus;
        this.mTime = mTime;
        this.mMessagestypeid = mMessagestypeid;
        this.mFromuserid = mFromuserid;
        this.mTouserid = mTouserid;
        this.mPostmessages = mPostmessages;
    }

    public Message() {
        super();
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Boolean getmStatus() {
        return mStatus;
    }

    public void setmStatus(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Integer getmMessagestypeid() {
        return mMessagestypeid;
    }

    public void setmMessagestypeid(Integer mMessagestypeid) {
        this.mMessagestypeid = mMessagestypeid;
    }

    public String getmFromuserid() {
        return mFromuserid;
    }

    public void setmFromuserid(String mFromuserid) {
        this.mFromuserid = mFromuserid == null ? null : mFromuserid.trim();
    }

    public String getmTouserid() {
        return mTouserid;
    }

    public void setmTouserid(String mTouserid) {
        this.mTouserid = mTouserid == null ? null : mTouserid.trim();
    }

    public String getmPostmessages() {
        return mPostmessages;
    }

    public void setmPostmessages(String mPostmessages) {
        this.mPostmessages = mPostmessages == null ? null : mPostmessages.trim();
    }
}
