package com.gis_server.service;

import com.gis_server.common.entity.Pager;
import com.gis_server.pojo.Device;

public interface DeviceService {

    int addNewDevice(Device device);

    int updateDevice(Device device);

    int deleteDevice(Integer id);

    Pager<Device> selectByPage(Integer uId,
                               Integer pageNum,
                               Integer pageSize,
                               String search);

    Device selectByName(String name);

}
