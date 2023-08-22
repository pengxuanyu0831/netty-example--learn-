package tools;

import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.Marshaller;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/22 21:03
 */
public class MarshallingEncoder {


    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    Marshaller marshaller;

    private final MarshallerProvider provider;

    public MarshallingEncoder(final MarshallerProvider provider) {
        this.provider = provider;
    }



}
