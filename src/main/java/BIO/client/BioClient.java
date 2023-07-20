package BIO.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @program springboot-netty
 * @description:
 * @author: pengxuanyu
 * @create: 2022/04/22 20:13
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",6666);
        System.out.printf("BIO Client start !");

        BIOClientHandler bioClientHandler = new BIOClientHandler(socket, Charset.forName("GBK"));
        bioClientHandler.start();
    }
}
