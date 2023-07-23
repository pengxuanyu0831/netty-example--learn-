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
    public static void main(String[] args) throws IOException {
        // NIO引入了selectot的概念，服务端的selectot会轮训是否有新的连接
        // 一旦出现新的连接，会自动绑定到clientSeletcot上，形成复用
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                ServerSocketChannel listenChannel = ServerSocketChannel.open();
                listenChannel.socket().bind(new InetSocketAddress(3333));
                // 非阻塞模式，有可能未读取到数据就返回了
                listenChannel.configureBlocking(false);
                // 注册方法
                // 生成SelectionKey 将channel 和selectionKey对应存放在一个concurrentHashMap中。
                listenChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                // 轮训监听
                while (true) {
                    // 不断检测是否有新的连接
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            if (next.isAcceptable()) {
                                try {
                                    // 当出现新连接的时候，把连接注册到clientSelector上
                                    SocketChannel socketChannel = ((ServerSocketChannel) next.channel()).accept();
                                    socketChannel.configureBlocking(false);
                                    socketChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
            }
        }).start();
        new Thread(() -> {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if (key.isReadable()) {
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    // (3) 面向 Buffer
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(
                                            Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }

                        }
                    }
                }
            } catch (IOException ignored) {
            }
        }).start();
    }
}
