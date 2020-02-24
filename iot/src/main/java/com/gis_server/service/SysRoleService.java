package com.gis_server.service;

import com.gis_server.common.entity.Pager;
import com.gis_server.dto.RoleDto;
import com.gis_server.pojo.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> findRoleListByULoginId(String uLoginid);

    Pager<RoleDto> getRoleByPage(Integer pageNum, Integer pageSize);

    Integer addRole(RoleDto roleDto);

    Integer updateRole(RoleDto role);

    Integer deleteRole(Integer id);

}
