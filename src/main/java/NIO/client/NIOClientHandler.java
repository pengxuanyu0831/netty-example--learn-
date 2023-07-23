package NIO.client;

import NIO.ChannelAdapter;
import NIO.ChannelHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/23 21:39
 */
public class NIOClientHandler extends ChannelAdapter {
    public NIOClientHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.printf("连接提示 ： LocalAddress: " + ctx.channel().getLocalAddress() + "\n");
            ctx.writeAndFlush("客户端握手成功！\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.printf("接收到服务端消息：" + msg + "\n");

        ctx.writeAndFlush("客户端信息处理Success！\n");
    }
}
