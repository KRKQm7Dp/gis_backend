package com.gis_server.mapper;

import com.gis_server.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> queryMsgByPage(@Param("from") String from,
                                 @Param("to") String to,
                                 @Param("date") Date date,
                                 @Param("msgNum") int msgNum);
}
