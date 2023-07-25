package org.nt.nettty.server;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/25 22:20
 */
public class NettyServer {


    private void bing(int port) {
        //配置服务端NIO线程组
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();



    }
}
