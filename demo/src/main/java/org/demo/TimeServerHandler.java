package org.demo;

import io.netty.channel.*;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/31 22:41
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
