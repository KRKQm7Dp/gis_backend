package com.gis_server;

import com.gis_server.mapper.SysRoleMapper;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.SysRoleService;
import com.gis_server.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
        String hashpw = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(hashpw);

        boolean checkpw = BCrypt.checkpw("secret", hashpw);
        System.out.println(checkpw);
    }



}
