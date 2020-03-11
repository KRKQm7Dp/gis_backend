package com.gis_server.service;

import com.gis_server.pojo.TempHum;

import java.util.List;

public interface TempHumService {

    int insertTempHum(TempHum tempHum);

    List<TempHum> getTempHumListByDeviceId(Integer deviceId, Integer size);

    TempHum getLastTempHumByDeviceId(Integer deviceId);

}
