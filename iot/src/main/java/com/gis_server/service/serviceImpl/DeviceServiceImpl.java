package com.gis_server.service.serviceImpl;

import com.gis_server.common.entity.Pager;
import com.gis_server.mapper.DeviceMapper;
import com.gis_server.pojo.Device;
import com.gis_server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("deviceService")
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public int addNewDevice(Device device) {
        return deviceMapper.insertSelective(device);
    }

    @Override
    public int updateDevice(Device device) {
        return deviceMapper.updateByPrimaryKeySelective(device);
    }

    @Override
    public int deleteDevice(Integer id) {
        return deviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Pager<Device> selectByPage(Integer uId,
                                       Integer pageNum,
                                       Integer pageSize,
                                      String search) {
        Pager<Device> pager = new Pager<>();
        pager.setPage(pageNum);
        pager.setSize(pageSize);
        pager.setRows(deviceMapper.selectByPage(uId,(pageNum - 1) * pageSize, pageSize, search));
        pager.setTotal(deviceMapper.countByUserId(uId));
        return pager;
    }

    @Override
    public List<Device> selectAllDevices(Integer uId) {
        return deviceMapper.selectAllDevices(uId);
    }

    @Override
    public Device selectByName(String name) {
        return deviceMapper.selectByName(name);
    }

    @Override
    public Device selectById(Integer id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

}
