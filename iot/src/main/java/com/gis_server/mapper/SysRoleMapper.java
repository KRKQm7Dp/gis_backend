package com.gis_server.mapper;


import com.gis_server.dto.RoleDto;
import com.gis_server.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    List<SysRole> selectRoleByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    List<RoleDto> selectRoleDtoByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    long countRole();

    List<SysRole> selectListByLoginId(String uLoginid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
