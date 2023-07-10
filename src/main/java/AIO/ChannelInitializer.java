package AIO;

import AIO.server.AIOServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/10 20:55
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AIOServer> {

    @Override
    public void completed(AsynchronousSocketChannel result, AIOServer attachment) {
        try {
            initChannel(result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            attachment.serverSocketChannel().accept(attachment, this);
        }
    }


    @Override
    public void failed(Throwable exc, AIOServer attachment) {

    }

    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;
}
