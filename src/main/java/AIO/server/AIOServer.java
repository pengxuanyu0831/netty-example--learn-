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
            System.out.printf("AIO Server Start! ---> port " + 7394 + "\n");
            CountDownLatch latch = new CountDownLatch(1);
            socketChannel.accept(this, new AioServerChannelInitializer());
            latch.await();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel serverSocketChannel() {
        return socketChannel;
    }


    public static void main(String[] args) {
        new AIOServer().start();
    }
}
