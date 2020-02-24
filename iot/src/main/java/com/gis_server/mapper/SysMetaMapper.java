package com.gis_server.mapper;


import com.gis_server.pojo.SysMeta;

public interface SysMetaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMeta record);

    int insertSelective(SysMeta record);

    SysMeta selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMeta record);

    int updateByPrimaryKey(SysMeta record);
}
