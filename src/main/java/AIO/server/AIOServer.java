package AIO.server;

import io.netty.channel.ChannelInitializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/5/15 23:10
 */
public class AIOServer extends Thread{
    private AsynchronousServerSocketChannel socketChannel;

    @Override
    public void run() {
        try {
            socketChannel = AsynchronousServerSocketChannel.open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10));
            socketChannel.bind(new InetSocketAddress(7394));

            CountDownLatch latch = new CountDownLatch(10);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
