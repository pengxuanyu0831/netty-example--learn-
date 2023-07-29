package org.nt.nettty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.DateFormat;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/29 16:37
 */
public class MyServerHadler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];

        buf.readBytes(bytes);

        System.out.printf(DateFormat.getDateTimeInstance().format(System.currentTimeMillis()) + "接收到消息：");
        System.out.printf(new String(bytes, "GBK"));
    }
}
