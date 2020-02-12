package com.gis_server.service.serviceImpl;

import com.gis_server.common.entity.Pager;
import com.gis_server.mapper.SysRoleMapper;
import com.gis_server.pojo.SysRole;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findRoleListByULoginId(String uLoginid) {
        return sysRoleMapper.selectListByLoginId(uLoginid);
    }

    @Override
    public Pager<SysRole> getRoleByPage(Integer pageNum, Integer pageSize) {
        Pager<SysRole> pager = new Pager<>();
        pager.setPage(pageNum);
        pager.setSize(pageSize);
        pager.setRows(sysRoleMapper.selectRoleByPage((pageNum-1)*pageSize, pageSize));
        pager.setTotal(sysRoleMapper.countRole());
        return pager;
    }

    @Override
    public Integer addRole(SysRole role) {
        return sysRoleMapper.insertSelective(role);
    }

    @Override
    public Integer updateRole(SysRole role) {
        return sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }
}
