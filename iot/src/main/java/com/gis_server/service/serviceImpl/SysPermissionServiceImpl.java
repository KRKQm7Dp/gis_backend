package com.gis_server.service.serviceImpl;

import com.gis_server.dto.PermissionDto;
import com.gis_server.mapper.SysPermissionMapper;
import com.gis_server.service.SysPermissionService;
import com.gis_server.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public List<PermissionDto> findPermissionList() {
        List<PermissionDto> permissions = sysPermissionMapper.selectAllPermission();
        List<PermissionDto> array = new ArrayList<>();
        TreeUtils.setPermissionsTree(0, permissions, array);
        return array;
    }

}
