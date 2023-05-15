package AIO;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/5/14 18:54
 */
public abstract class ChannelAdapter implements CompletionHandler<Integer, Object> {
    private AsynchronousSocketChannel channel;
    private Charset charset;

    public abstract void channelActive(final ChannelHandler ctx) throws IOException;

    public abstract void channelInactive(final ChannelHandler ctx);

    public abstract void channelRead(final ChannelHandler ctx, final Object msg);

    public ChannelAdapter(final AsynchronousSocketChannel channel, final Charset charset) throws IOException {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()) {
            channelActive(new ChannelHandler(channel, charset));
        }
    }

    @Override
    public void completed(Integer result, Object attachment) {

    }

    @Override
    public void failed(Throwable exc, Object attachment) {

    }

}
