package BIO.client;

import java.io.IOException;
import java.net.Socket;
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
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            socket.getOutputStream().write(scanner.nextLine().getBytes());
        }
    }
}
