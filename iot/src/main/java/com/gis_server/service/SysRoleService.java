package com.gis_server.service;

import com.gis_server.common.entity.Pager;
import com.gis_server.pojo.SysRole;
import com.gis_server.pojo.SysUser;

import java.util.List;

public interface SysRoleService {

    List<SysRole> findRoleListByULoginId(String uLoginid);

    Pager<SysRole> getRoleByPage(Integer pageNum, Integer pageSize);

    Integer addRole(SysRole role);

    Integer updateRole(SysRole role);

    Integer deleteRole(Integer id);

}
