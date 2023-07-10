package AIO.client;

import AIO.ChannelAdapter;
import AIO.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author xuanyu peng
 * @description: AIO是异步非阻塞的IO。是一种非阻塞异步的通信模式。在NIO的基础上引入了新的异步通道的概念，并提供了异步文件通道和异步套接字通道的实现
 * @deprecated 客户端-消息处理器
 * @date 2023/5/14 18:51
 */
public class AIOClientHandler extends ChannelAdapter {
    public AIOClientHandler(final AsynchronousSocketChannel channel, final Charset charset) throws IOException {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) throws IOException {
        try {
            System.out.printf("AIO Client channelActive Done! " + ctx.channel().getRemoteAddress() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    /**
     * 服务端读取数据
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.printf("服务端 于" + new Date() + "收到消息！：" + msg);
        ctx.writeAndFlush(msg);
    }
}
