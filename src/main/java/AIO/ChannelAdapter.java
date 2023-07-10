package AIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

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
        try {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            final long time = 60 * 60L;
            channel.read(buffer, time, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {


                @Override
                public void completed(Integer result, Object attachment) {
                    if (result == -1) {
                        try {
                            channelInactive(new ChannelHandler(channel, charset));
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    channelRead(new ChannelHandler(channel, charset), charset.decode(buffer));
                    buffer.clear();
                    channel.read(buffer, time, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();

    }

}
