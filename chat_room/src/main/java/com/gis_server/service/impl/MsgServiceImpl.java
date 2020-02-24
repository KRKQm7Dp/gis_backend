package com.gis_server.service.impl;

import com.gis_server.mapper.MessageMapper;
import com.gis_server.pojo.Message;
import com.gis_server.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("msgService")
@Transactional
public class MsgServiceImpl implements MsgService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Integer insertMsgToDB(Message msg) {
        return messageMapper.insertSelective(msg);
    }

    @Override
    public List<Message> queryMsgByPage(String from, String to, Date date, int msgNum) {
        return messageMapper.queryMsgByPage(from, to, date, msgNum);
    }


}
