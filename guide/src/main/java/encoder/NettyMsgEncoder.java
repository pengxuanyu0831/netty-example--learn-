package encoder;

import dto.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/22 20:54
 */
public final class NettyMsgEncoder extends MessageToMessageEncoder<Msg> {
    MarshallingEncoder marshallingEncoder;

    public NettyMsgEncoder(MarshallingEncoder marshallingEncoder) {
        this.marshallingEncoder = marshallingEncoder;
    }

    @Override
    protected void encode(ChannelHandlerContext chx, Msg msg, List<Object> list) throws Exception {
        if(msg == null || msg.getHeader() == null){
            throw new Exception("The encode message is null");
        }

        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(msg.getHeader().getCrcCode());
        buffer.writeInt(msg.getHeader().getLength());
        buffer.writeLong(msg.getHeader().getSessionID());
        buffer.writeByte(msg.getHeader().getType());
        buffer.writeByte(msg.getHeader().getPriority());
        buffer.writeInt(msg.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;

        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            buffer.writeInt(keyArray.length);
            buffer.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(chx, value, buffer);
        }

        key = null;
        keyArray = null;
        value = null;

        if (msg.getBody() != null) {
            marshallingEncoder.encode(chx, msg.getBody(), buffer);
        } else {
            buffer.writeInt(0);
            buffer.setInt(4, buffer.readableBytes());
        }
    }

}
