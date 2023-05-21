package AIO.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/5/21 22:32
 */
public class AioServerChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
//        channel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS, null, new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
