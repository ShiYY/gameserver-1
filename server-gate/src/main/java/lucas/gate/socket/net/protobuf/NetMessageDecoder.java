package lucas.gate.socket.net.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lucas.common.GlobalConstant;
import lucas.gate.socket.net.message.AbstractNetMessage;
import lucas.gate.socket.net.message.NetMessageBody;
import lucas.gate.socket.net.message.NetMessageHead;

import java.util.List;

/**
 * @author lushengkao vip8
 * 2018/11/7 16:31
 */
public class NetMessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        short magicCode = msg.readShort();
        //协议有问题 关闭
        if (magicCode != GlobalConstant.getMagicCode()) {
            ctx.fireChannelInactive();
            return;
        }
        byte version = msg.readByte();
        if (version != GlobalConstant.getVERSION()) {
            ctx.fireChannelInactive();
            return;
        }
        AbstractNetMessage protoBufNetMessage = new AbstractNetMessage();
        NetMessageHead head = protoBufNetMessage.getHead();
        NetMessageBody body = protoBufNetMessage.getBody();
        head.setVersion(version);
        head.setLength(msg.readInt());
        head.setSerial(msg.readInt());
        head.setCommand(msg.readInt());
        int readableBytes = msg.readableBytes();
        byte[] bytes = new byte[readableBytes];
        msg.getBytes(msg.readerIndex(),bytes);
        body.setBytes(bytes);
        out.add(protoBufNetMessage);
    }
}
