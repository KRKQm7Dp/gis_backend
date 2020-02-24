package com.gis_server.service;

import com.gis_server.dto.UserDto;
import com.gis_server.pojo.Friend;

import java.util.List;

public interface FriendService {

    List<UserDto> queryMyFriends(String uLoginid);

    int addFriendRelationship(Friend friend);

}
