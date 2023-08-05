package org.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/31 21:48
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(7394));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 创建一个选择器
        Selector open = Selector.open();
        new Thread(() -> {
            while (true) {
                try {
                    serverSocketChannel.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
}
