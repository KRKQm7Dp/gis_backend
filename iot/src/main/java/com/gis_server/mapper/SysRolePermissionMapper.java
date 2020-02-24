package com.gis_server.mapper;


import com.gis_server.pojo.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(SysRolePermission key);

    int deleteByRoleId(@Param("roleId") Integer roleId);

    int insert(SysRolePermission record);

    int insertLoop(@Param("roleId") Integer roleId, @Param("permissionIds") List<Integer> permissionIds);

    int insertSelective(SysRolePermission record);
}
