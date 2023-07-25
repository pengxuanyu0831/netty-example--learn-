package org.nt.nettty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/25 22:20
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.printf("链接报告开始");
        System.out.printf("链接报告信息：有一客户端链接到本服务端");
        System.out.printf("链接报告IP:" + ch.localAddress().getHostString());
        System.out.printf("链接报告Port:" + ch.localAddress().getPort());
        System.out.printf("链接报告完毕");
    }
}
