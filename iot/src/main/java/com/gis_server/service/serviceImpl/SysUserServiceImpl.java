package com.gis_server.service.serviceImpl;

import com.gis_server.common.entity.Pager;
import com.gis_server.mapper.SysUserMapper;
import com.gis_server.mapper.SysUserRoleMapper;
import com.gis_server.pojo.SysRole;
import com.gis_server.pojo.SysUser;
import com.gis_server.pojo.SysUserRole;
import com.gis_server.service.SysRoleService;
import com.gis_server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRoleService sysRoleService;

    @Override
    public SysUser findUserByLoginID(String uLoginid) {
        return sysUserMapper.selectByLoginId(uLoginid);
    }

    @Override
    public List<SysUser> getAllUser() {
        return sysUserMapper.selectAllUser();
    }

    @Override
    public Pager getUserByPage(Integer pageNum, Integer pageSize) {
        Pager<SysUser> pager = new Pager<>();
        pager.setPage(pageNum);
        pager.setSize(pageSize);
        List<SysUser> users = sysUserMapper.selectUserByPage((pageNum-1)*pageSize, pageSize);
        users.forEach(user -> {
            List<SysRole> roles = sysRoleService.findRoleListByULoginId(user.getuLoginid());
            user.setRoles(roles);
        });
        pager.setRows(users);
        pager.setTotal(sysUserMapper.countUser());
        return pager;
    }

    @Override
    public Integer addUser(SysUser user) {
        user.setuPassword(BCrypt.hashpw(user.getuPassword(), BCrypt.gensalt()));
        Integer status = sysUserMapper.insertSelective(user);
        if(status > 0){
            user.getRoles().forEach(sysRole -> {
                sysUserRoleMapper.insertSelective(new SysUserRole(user.getuId(), sysRole.getId()));
            });
        }
        return status;
    }

    @Override
    public Integer modifyUser(SysUser user) {
        Integer id = sysUserMapper.selectByLoginId(user.getuLoginid()).getuId();
        user.setuId(id);
        sysUserRoleMapper.deleteByUserId(id);
        user.getRoles().forEach(sysRole -> {
            sysUserRoleMapper.insertSelective(new SysUserRole(id, sysRole.getId()));
        });
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }


    @Override
    public SysUser queryUserByLoginID(String loginId) {
        return sysUserMapper.selectByLoginId(loginId);
    }
}
