package BIO.server;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @program springboot-netty
 * @description: BIO=同步阻塞模型，请求线程和处理线程是同一个线程，在完成读取前，线程阻塞
 * @author: pengxuanyu
 * @create: 2022/04/22 20:07
 */
public class BioServer extends Thread {
    private ServerSocket serverSocket = null;

    public static void main(String[] args) throws IOException {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(6666));

            System.out.printf("BIO Server start !");

            while (Boolean.TRUE) {
                Socket socket = serverSocket.accept();
                BIOServerHandler handler = new BIOServerHandler(socket, Charset.forName("GBK"));
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
