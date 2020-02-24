package com.gis_server.service.impl;

import com.gis_server.mapper.SysUserMapper;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser queryUserByLoginID(String loginId) {
        return sysUserMapper.selectByLoginId(loginId);
    }

    @Override
    public int updateUserInfo(SysUser user) {
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }
}
