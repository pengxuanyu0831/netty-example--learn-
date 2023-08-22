package tools;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/22 21:12
 */
public class MarshaliingCodeCFactory {
    public static MarshallingDecoder buildMarshallingDecoder() {

//        final MarshallingDecoder decoder = new MarshallingDecoder(null);

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(7);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        return new MarshallingDecoder(provider, 1024);
    }


    public static MarshallingEncoder buildMarshallingEncoder() {

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(7);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        return new MarshallingEncoder(provider);
    }
}
