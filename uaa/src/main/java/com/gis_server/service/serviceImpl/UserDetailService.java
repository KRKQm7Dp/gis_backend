package com.gis_server.service.serviceImpl;

import com.gis_server.pojo.SysRole;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.SysRoleService;
import com.gis_server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 结合具体的逻辑去实现用户认证，并返回继承UserDetails的用户对象;
        System.out.println("当前的用户名是："+username);

        SysUser sysUser = sysUserService.findUserByLoginID(username);
        if(sysUser == null){
            return null;
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        List<SysRole> roles = sysRoleService.findRoleListByULoginId(username);
        roles.forEach(item -> {
            authorities.add(new SimpleGrantedAuthority(item.getRoleName()));
        });

        UserDetails userDetails = new User(sysUser.getuLoginid(),
                sysUser.getuPassword(),
                sysUser.getEnabled(),
                sysUser.getNotExpired(),
                sysUser.getCredentialsNotExpired(),
                sysUser.getAccountNotLocked(),
                authorities);
        return userDetails;
    }
}
