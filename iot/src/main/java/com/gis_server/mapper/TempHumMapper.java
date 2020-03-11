package com.gis_server.mapper;


import com.gis_server.pojo.TempHum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TempHumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempHum record);

    int insertSelective(TempHum record);

    TempHum selectByPrimaryKey(Integer id);

    List<TempHum> selectByDeviceId(@Param("deviceId") Integer deviceId,
                                   @Param("size") Integer size);

    TempHum selectLastByDeviceId(@Param("deviceId") Integer deviceId);

    int updateByPrimaryKeySelective(TempHum record);

    int updateByPrimaryKey(TempHum record);
}
