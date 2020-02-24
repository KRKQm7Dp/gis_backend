package com.gis_server.service;

import com.gis_server.dto.PermissionDto;

import java.util.List;

public interface SysPermissionService {

    List<PermissionDto> findPermissionList();

}
