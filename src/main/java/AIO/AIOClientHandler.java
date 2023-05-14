package AIO;

import io.netty.channel.ChannelHandlerContext;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/5/14 18:51
 */
public class AIOClientHandler extends ChannelAdapter {
    public AIOClientHandler(final AsynchronousSocketChannel channel, final Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {

    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

    }
}
