package com.gis_server.service.serviceImpl;

import com.gis_server.mapper.SysRoleMapper;
import com.gis_server.pojo.SysRole;
import com.gis_server.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findRoleListByULoginId(String uLoginid) {
        return sysRoleMapper.selectListByLoginId(uLoginid);
    }
}
