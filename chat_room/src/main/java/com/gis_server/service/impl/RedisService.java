package com.gis_server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void insertOfflineMsg(String toUserLoginID, String msg){
        redisTemplate.opsForList().rightPush(toUserLoginID, msg);   // 后进
    }

    public List<String> getOfflineMsg(String loginUserID){
        return redisTemplate.opsForList().range(loginUserID,0, -1);  // 先出
    }

    public boolean removeOffLineMsg(String loginUserID){
        return redisTemplate.delete(loginUserID);
    }

}
