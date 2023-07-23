package NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/23 21:44
 */
public class ChannelHandler {
    private SocketChannel socketChannel;

    private Charset charset;

    public ChannelHandler(final SocketChannel socketChannel, final Charset charset) {
        this.socketChannel = socketChannel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        try {
            byte[] bytes = msg.toString().getBytes(charset);

            // 这是创建buffer缓冲区，需要指定缓冲区大小
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
            // 存放数据
            allocate.put(bytes);
            // 读写转换
            allocate.flip();

            socketChannel.write(allocate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel channel() {
        return socketChannel;
    }
}
