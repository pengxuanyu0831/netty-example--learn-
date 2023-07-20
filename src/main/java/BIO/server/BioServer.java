package BIO.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program springboot-netty
 * @description: BIO=同步阻塞模型，请求线程和处理线程是同一个线程，在完成读取前，线程阻塞
 * @author: pengxuanyu
 * @create: 2022/04/22 20:07
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        // 阻塞， 直到有客户端接入
        while (true) {
            Socket socket = serverSocket.accept();
            int len = -1;
            // 读取的数据长度
            byte[] data = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            int i = inputStream.read(data);
            while (i != -1) {
                System.out.println(new String(data, 0, len));
            }
        }
    }
}
