package com.gis_server.mapper;


import com.gis_server.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    Device selectByName(@Param("name") String name);

    List<Device> selectByPage(@Param("userId") Integer uId,
                            @Param("pageNum") Integer pageNum,
                            @Param("pageSize") Integer pageSize,
                              @Param("search") String search);

    List<Device> selectAllDevices(@Param("userId") Integer uId);

    Long countByUserId(@Param("userId") Integer uId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}
