package AIO.server;

import AIO.ChannelAdapter;
import AIO.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/10 21:09
 */
public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(final AsynchronousSocketChannel channel, final Charset charset) throws IOException {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) throws IOException {
        System.out.printf("链接报告信息：客户端链接到AIO服务端，channelId: " +  ctx.channel().getRemoteAddress() +"\n");
        ctx.writeAndFlush("服务端链接成功！\n");
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.printf("AIO服务端收到：" + msg + "\n");

        ctx.writeAndFlush("AIO服务端信息处理Success！\n");
    }
}
