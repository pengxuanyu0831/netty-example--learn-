package NIO.server;

import NIO.ChannelAdapter;
import NIO.ChannelHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/23 22:20
 */
public class NIOServerHandler extends ChannelAdapter {

    public NIOServerHandler(final Selector selector, final Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) throws IOException {
        System.out.printf("连接报告LocalAddress:" + ctx.channel().getLocalAddress());

        ctx.writeAndFlush("hi! 我是NIO服务端\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

        System.out.printf("接收到消息：" + msg + "\n");

        ctx.writeAndFlush("hi 我是NIO服务端 我已经收到你的消息Success！\r\n");
    }
}
