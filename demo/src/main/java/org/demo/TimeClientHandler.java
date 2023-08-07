package org.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.io.UnsupportedEncodingException;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/5 15:23
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] bytes = "QUERY TIME ORDER".getBytes();

        firstMessage = Unpooled.buffer(bytes.length);
        firstMessage.writeBytes(bytes);
    }


    @Override
    public void channelActive(ChannelHandlerContext msg) {
        msg.writeAndFlush(firstMessage);
    }


    // 服务端返回应答消息时，此方法被调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);

        String body = new String(bytes, "UTF-8");
        System.out.println("Now is : " + body);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
