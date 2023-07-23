package NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/23 21:40
 */
public abstract class ChannelAdapter extends Thread{

    private Selector selector;

    private ChannelHandler channelHandler;

    private Charset charset;

    public ChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = keys.iterator();

                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();

                    handleInput(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) return;


        Class<?> superclass = key.channel().getClass().getSuperclass();

        // 客户端SocketChannel
        if(superclass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    channelHandler = new ChannelHandler(socketChannel, charset);
                    channelActive(channelHandler);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                // todo 为什么要退出
                System.exit(1);
            }
        }


        if (superclass == ServerSocketChannel.class) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();

                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

                channelHandler = new ChannelHandler(socketChannel, charset);
                channelActive(channelHandler);
            }
        }


        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();

            ByteBuffer allocate = ByteBuffer.allocate(1024);

            int read = socketChannel.read(allocate);

            if (read > 0) {
                allocate.flip();

                byte[] bytes = new byte[allocate.remaining()];

                allocate.get(bytes);

                channelRead(channelHandler, new String(bytes, charset));
            }else if(read < 0) {
                key.cancel();
                socketChannel.close();
            }
        }


    }


    // 链接通知抽象类
    public abstract void channelActive(ChannelHandler ctx) throws IOException;

    // 读取消息抽象类
    public abstract void channelRead(ChannelHandler ctx, Object msg);

}
