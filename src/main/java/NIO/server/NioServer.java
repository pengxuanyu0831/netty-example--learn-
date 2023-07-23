package NIO.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @program springboot-netty
 * @description:
 * @author: pengxuanyu
 * @create: 2022/04/22 20:07
 */
public class NioServer {

    private Selector selector;
    private ServerSocketChannel socketChannel;


    public static void main(String[] args) throws IOException {
        new NioServer().bind(7397);
    }

    public void bind(int port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);

            socketChannel.socket().bind(new InetSocketAddress(port), 1024);

            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.printf("NIO Server start !");

            new NIOServerHandler(selector, Charset.forName("GBK")).start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
