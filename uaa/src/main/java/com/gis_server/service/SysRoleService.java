package com.gis_server.service;

import com.gis_server.pojo.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> findRoleListByULoginId(String uLoginid);

}
