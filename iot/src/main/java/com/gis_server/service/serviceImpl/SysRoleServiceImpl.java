package com.gis_server.service.serviceImpl;

import com.gis_server.common.entity.Pager;
import com.gis_server.dto.RoleDto;
import com.gis_server.mapper.SysRoleMapper;
import com.gis_server.mapper.SysRolePermissionMapper;
import com.gis_server.pojo.SysRole;
import com.gis_server.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysRole> findRoleListByULoginId(String uLoginid) {
        return sysRoleMapper.selectListByLoginId(uLoginid);
    }

    @Override
    public Pager<RoleDto> getRoleByPage(Integer pageNum, Integer pageSize) {
        Pager<RoleDto> pager = new Pager<>();
        pager.setPage(pageNum);
        pager.setSize(pageSize);
        pager.setRows(sysRoleMapper.selectRoleDtoByPage((pageNum - 1) * pageSize, pageSize));
        pager.setTotal(sysRoleMapper.countRole());
        return pager;
    }

    @Override
    public Integer addRole(RoleDto roleDto) {
        sysRoleMapper.insertSelective(roleDto);
        List<Integer> permissionIds = roleDto.getPermissionIds();
        if (!CollectionUtils.isEmpty(permissionIds)) {
//            permissionIds.remove(0);
            sysRolePermissionMapper.insertLoop(roleDto.getId(), permissionIds);
        }
        return roleDto.getId();
    }

    @Override
    public Integer updateRole(RoleDto role) {
        List<Integer> permissionIds = role.getPermissionIds();

        sysRolePermissionMapper.deleteByRoleId(role.getId());

        if (!CollectionUtils.isEmpty(permissionIds)) {
            sysRolePermissionMapper.insertLoop(role.getId(), permissionIds);
        }

        Integer res = sysRoleMapper.updateByPrimaryKeySelective(role);

        return res;
    }

    @Override
    public Integer deleteRole(Integer id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }
}
