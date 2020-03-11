package com.gis_server.service;

import com.gis_server.common.entity.Pager;
import com.gis_server.pojo.Device;

import java.util.List;

public interface DeviceService {

    int addNewDevice(Device device);

    int updateDevice(Device device);

    int deleteDevice(Integer id);

    Pager<Device> selectByPage(Integer uId,
                               Integer pageNum,
                               Integer pageSize,
                               String search);

    List<Device> selectAllDevices(Integer uId);

    Device selectByName(String name);

    Device selectById(Integer id);

}
