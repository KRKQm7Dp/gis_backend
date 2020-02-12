package com.gis_server.service.serviceImpl;

import com.gis_server.common.entity.Pager;
import com.gis_server.mapper.SysUserMapper;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserByLoginID(String uLoginid) {
        return sysUserMapper.selectByLoginId(uLoginid);
    }

    @Override
    public List<SysUser> getAllUser() {
        return sysUserMapper.selectAllUser();
    }

    @Override
    public Pager<SysUser> getUserByPage(Integer pageNum, Integer pageSize) {
        Pager<SysUser> pager = new Pager<>();
        pager.setPage(pageNum);
        pager.setSize(pageSize);
        pager.setRows(sysUserMapper.selectUserByPage((pageNum-1)*pageSize, pageSize));
        pager.setTotal(sysUserMapper.countUser());
        return pager;
    }

    @Override
    public void addUser(SysUser user) {
        sysUserMapper.insertSelective(user);
    }

    @Override
    public void modifyUser(SysUser user) {
        user.setuId(sysUserMapper.selectByLoginId(user.getuLoginid()).getuId());
        sysUserMapper.updateByPrimaryKeySelective(user);
    }
}
