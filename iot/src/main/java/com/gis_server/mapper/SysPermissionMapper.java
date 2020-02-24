package com.gis_server.mapper;

import com.gis_server.dto.PermissionDto;
import com.gis_server.pojo.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    List<PermissionDto> selectAllPermission();

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}
