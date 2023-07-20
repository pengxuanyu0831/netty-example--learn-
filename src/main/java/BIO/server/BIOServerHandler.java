package BIO.server;

import BIO.ChannelAdapter;
import BIO.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/20 21:17
 */
public class BIOServerHandler extends ChannelAdapter {

    public BIOServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.printf("连接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! 我是BIO服务端\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.printf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "接收到消息：" + msg + "\n");
        ctx.writeAndFlush("hi 我是BIO服务端 我已经收到你的消息Success！\r\n");
    }
}
