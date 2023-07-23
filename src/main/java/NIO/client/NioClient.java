package NIO.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/23 22:13
 */
public class NioClient {


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();


        SocketChannel open = SocketChannel.open();

        open.configureBlocking(false);

        boolean b = open.connect(new InetSocketAddress("127.0.0.1", 7394));

        if (b) {
            open.register(selector, SelectionKey.OP_READ);
        } else {
            open.register(selector, SelectionKey.OP_CONNECT);
        }

        System.out.printf("NIO Client Start! \n");

        new NIOClientHandler(selector, Charset.forName("GBK")).start();
    }
}
