package com.gis_server.mapper;


import com.gis_server.pojo.TempHum;

public interface TempHumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempHum record);

    int insertSelective(TempHum record);

    TempHum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempHum record);

    int updateByPrimaryKey(TempHum record);
}
