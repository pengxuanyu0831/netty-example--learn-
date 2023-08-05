package org.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/5 15:23
 */
public class TimeClientHandler extends ChannelHandlerAdapter {


    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] bytes = "QUERY TIME ORDER".getBytes();

        firstMessage = Unpooled.buffer(bytes.length);
        firstMessage.writeBytes(bytes);
    }


    public void channelActive(ChannelHandlerContext msg) {
        msg.writeAndFlush(firstMessage);
    }

}
