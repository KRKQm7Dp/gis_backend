package com.gis_server;

import com.gis_server.iot.IotServer;
import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.gis_server.mapper")
@SpringBootApplication
public class IOTSpringbootApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Autowired
    private IotServer echoServer;

    public static void main(String[] args) {
        SpringApplication.run(IOTSpringbootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = echoServer.start(port);
//        Runtime.getRuntime().addShutdownHook(new Thread(){
//            @Override
//            public void run() {
//                echoServer.destroy();
//            }
//        });
//        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
//        future.channel().closeFuture().syncUninterruptibly();
    }
}
