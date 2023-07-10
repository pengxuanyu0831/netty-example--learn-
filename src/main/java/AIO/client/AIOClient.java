package AIO.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/5/15 23:05
 */
public class AIOClient {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = asynchronousSocketChannel.connect(new InetSocketAddress("localhost", 7394));
        System.out.printf("AIO Client Start! \n");
        future.get();
        asynchronousSocketChannel.read(ByteBuffer.allocate(1024), null, new AIOClientHandler(asynchronousSocketChannel, Charset.forName("GBK")));
        System.out.printf("AIO Client Start Done! \n");

    }
}
