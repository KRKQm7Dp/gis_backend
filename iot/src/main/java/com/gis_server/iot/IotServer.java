package com.gis_server.iot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IotServer {

    private static final Logger log = LoggerFactory.getLogger(IotServer.class);

//    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
//    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public ChannelFuture start(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ChannelFuture f = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChildChannelHandler())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("SimpleChatServer 启动了");

            // 绑定端口，开始接收进来的连接
            f = b.bind(port).sync();

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            System.out.println("SimpleChatServer 关闭了");
        }
        return f;
    }

//    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
//
//        @Override
//        protected void initChannel(SocketChannel socketChannel) throws Exception {
//
//            socketChannel.pipeline().addLast(new IotServerHandler());
//        }
//    }

//    /**
//     * 停止服务
//     */
//    public void destroy() {
//        log.info("Shutdown Netty Server...");
//        workerGroup.shutdownGracefully();
//        bossGroup.shutdownGracefully();
//        log.info("Shutdown Netty Server Success!");
//    }
}

