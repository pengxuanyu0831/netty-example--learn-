package AIO.server;

import AIO.AIOClientHandler;
import common.ChannelInitializer;
import io.netty.channel.Channel;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/5/21 22:32
 */
public class AioServerChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        channel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS, null, new AIOClientHandler(channel, Charset.forName("GBK")));
    }
}
