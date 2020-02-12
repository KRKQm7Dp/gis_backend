package com.gis_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.gis_server.mapper")
@SpringBootApplication
public class UAASpringbootApplication{

    public static void main(String[] args) {
        SpringApplication.run(UAASpringbootApplication.class, args);
    }

}
