package com.gis_server.service.impl;

import com.gis_server.dto.UserDto;
import com.gis_server.mapper.FriendMapper;
import com.gis_server.pojo.Friend;
import com.gis_server.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<UserDto> queryMyFriends(String uLoginid) {
        return friendMapper.selectByUserId(uLoginid);
    }

    @Override
    public int addFriendRelationship(Friend friend) {
        friendMapper.insertSelective(friend);
        String id = friend.getfFirendid();
        friend.setfFirendid(friend.getfUserid());
        friend.setfUserid(id);
        return friendMapper.insertSelective(friend);
    }
}
