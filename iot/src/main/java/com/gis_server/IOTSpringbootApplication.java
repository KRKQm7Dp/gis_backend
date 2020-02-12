package com.gis_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@MapperScan(value = "com.gis_server.mapper")
@SpringBootApplication
public class IOTSpringbootApplication{

    public static void main(String[] args) {
        SpringApplication.run(IOTSpringbootApplication.class, args);
    }

}
