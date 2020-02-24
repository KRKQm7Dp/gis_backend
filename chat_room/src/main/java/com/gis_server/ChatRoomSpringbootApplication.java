package com.gis_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@MapperScan(value = "com.gis_server.mapper")
@SpringBootApplication
public class ChatRoomSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatRoomSpringbootApplication.class, args);
    }

}
