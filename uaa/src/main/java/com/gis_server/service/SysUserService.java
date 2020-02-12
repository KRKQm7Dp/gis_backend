package com.gis_server.service;

import com.gis_server.common.entity.Pager;
import com.gis_server.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser findUserByLoginID(String uLoginid);

    List<SysUser> getAllUser();

    Pager<SysUser> getUserByPage(Integer pageNum, Integer pageSize);

    void addUser(SysUser user);

    void modifyUser(SysUser user);

}
