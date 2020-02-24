package com.gis_server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gis_server.dto.PermissionDto;
import com.gis_server.mapper.SysRoleMapper;
import com.gis_server.pojo.SysPermission;
import com.gis_server.service.SysPermissionService;
import com.gis_server.service.SysRoleService;
import com.gis_server.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class GisServerSpringbootApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysPermissionService sysPermissionService;

    @Autowired
    SysRoleMapper sysRoleMapper;


    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }

    @Test
    void roleMapperTest(){
        List list = sysUserService.getAllUser();
        list.forEach((r)->{
            System.out.println(r.toString());
        });
    }

    @Test
    void getUserByPageTest(){
        sysUserService.getUserByPage(1, 10);
    }

    @Test
    void testBCrypt(){
//        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
////        System.out.println(hashpw);
////
////        boolean checkpw = BCrypt.checkpw("secret", hashpw);
////        System.out.println(checkpw);
        String pass = new BCryptPasswordEncoder().encode("secret");
        System.out.println(pass);
    }

    @Test
    void testPermission() throws JsonProcessingException {
        List<PermissionDto> permissionList = sysPermissionService.findPermissionList();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(permissionList));
    }

    @Test
    void testRole() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(sysRoleMapper.selectRoleDtoByPage(0,3)));
    }



}
