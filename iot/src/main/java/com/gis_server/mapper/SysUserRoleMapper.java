package com.gis_server.mapper;


import com.gis_server.pojo.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    int deleteByUserId(int uLoginId);
}
