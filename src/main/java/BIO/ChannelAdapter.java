package BIO;

import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/7/16 22:19
 */
public abstract class ChannelAdapter extends Thread {
    private Socket socket;

    private Charset charset;
}
