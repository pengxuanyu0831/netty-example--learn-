package org.nt.nettty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/25 22:20
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    // 解码器
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 基于换行符号
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码字符串，调整自定义的编码格式
        ch.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new MyServerHadler());
    }
}
