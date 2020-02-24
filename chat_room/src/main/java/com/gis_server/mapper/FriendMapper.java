package com.gis_server.mapper;

import com.gis_server.dto.UserDto;
import com.gis_server.pojo.Friend;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer fId);

    List<UserDto> selectByUserId(String uLoginId);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}
