package com.gis_server.service;

import com.gis_server.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MsgService {

    Integer insertMsgToDB(Message msg);

    List<Message> queryMsgByPage(String from, String to, Date date, int msgNum);

}
