package com.gis_server.service.serviceImpl;

import com.gis_server.mapper.TempHumMapper;
import com.gis_server.pojo.TempHum;
import com.gis_server.service.TempHumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tempHumService")
@Transactional
public class TempHumServiceImpl implements TempHumService {

    @Autowired
    TempHumMapper tempHumMapper;

    @Override
    public int insertTempHum(TempHum tempHum) {
        return tempHumMapper.insertSelective(tempHum);
    }
}
