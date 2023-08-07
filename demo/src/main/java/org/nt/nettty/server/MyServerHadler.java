package org.nt.nettty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;


import java.text.DateFormat;
import java.util.Date;

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
        String str = "服务端收到：" + new Date() + " " + msg + "\r\n";
        ByteBuf buf = ctx.alloc().buffer(str.getBytes().length);
        buf.writeBytes(str.getBytes("GBK"));
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        System.out.printf("链接报告开始 \r\n");
        System.out.printf("链接报告IP:" + ch.localAddress().getHostString() + "\r\n");
        System.out.printf("链接报告Port:" + ch.localAddress().getPort()+"\r\n");
        String res = "[Netty]通知客户端连接建立成功" + " " + new Date() + " " + ch.localAddress().getHostString() + "\r\n";
        ByteBuf buf = ctx.alloc().buffer(res.getBytes().length);
        buf.readBytes(res.getBytes("GBK"));
        ctx.writeAndFlush(buf);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("客户端断开链接" + ctx.channel().localAddress().toString());
    }
}
