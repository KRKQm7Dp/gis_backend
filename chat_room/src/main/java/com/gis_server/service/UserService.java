package com.gis_server.service;

import com.gis_server.pojo.SysUser;

public interface UserService {

    SysUser queryUserByLoginID(String loginId);

    int updateUserInfo(SysUser user);

}
