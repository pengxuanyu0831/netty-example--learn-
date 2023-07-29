package org.nt.nettty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;


import java.text.DateFormat;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/29 16:37
 */
public class MyServerHadler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.printf(DateFormat.getDateTimeInstance().format(System.currentTimeMillis()) + "接收到消息：");
//        System.out.printf(new String(bytes, "GBK"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        System.out.printf("链接报告开始");
        System.out.printf("链接报告IP:" + ch.localAddress().getHostString());
        System.out.printf("链接报告Port:" + ch.localAddress().getPort());
    }
}
