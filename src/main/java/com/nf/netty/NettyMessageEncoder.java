package com.nf.netty;

import com.nf.entity.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * 服务端这里继承<code>MessageToByteEncoder</code>更加方便
 */
public class NettyMessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf byteBuf) throws Exception {
        int dataLength = msg.getData() == null ? 0 : msg.getData().length;

//        dataLength += msg.getUid().getBytes().length;

//        byteBuf.ensureWritable(4 + dataLength);
//        byteBuf.writeInt(dataLength);
        byteBuf.writeShort(msg.getCode());
        byteBuf.writeInt(msg.getUid().getBytes().length);
        byteBuf.writeCharSequence(msg.getUid(), CharsetUtil.UTF_8);
        if (dataLength > 0) {
            byteBuf.writeInt(dataLength);
            byteBuf.writeBytes(msg.getData());
        }
    }

}
