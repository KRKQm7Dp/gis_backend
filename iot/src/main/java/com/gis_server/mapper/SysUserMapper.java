package com.gis_server.mapper;


import com.gis_server.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer uId);

    SysUser selectByLoginId(String uLoginid);

    List<SysUser> selectAllUser();

    List<SysUser> selectUserByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    long countUser();

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
