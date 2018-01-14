package com.nf.netty;

import com.nf.entity.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 服务端这里继承<code>MessageToByteEncoder</code>更加方便
 */
public class NettyMessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf byteBuf) throws Exception {
        int dataLength = msg.getData() == null ? 0 : msg.getData().length;
        byteBuf.ensureWritable(4 + dataLength);
        byteBuf.writeInt(dataLength);
        byteBuf.writeShort(msg.getCode());
        if (dataLength > 0) {
            byteBuf.writeBytes(msg.getData());
        }
    }

}
