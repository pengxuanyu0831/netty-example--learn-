package org.nt.nettty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/25 22:20
 */
public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().bing(7397);
    }


    private void bing(int port) {
        //配置服务端NIO线程组
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    // 非阻塞模式
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer());

            ChannelFuture sync = serverBootstrap.bind(port).sync();
            System.out.printf("netty server2 start done!");
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }
}
