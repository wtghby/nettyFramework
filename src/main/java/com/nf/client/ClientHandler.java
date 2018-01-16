package com.nf.client;

import com.google.protobuf.ByteString;
import com.nf.entity.Message;
import com.nf.proto.DataProto;
import com.nf.proto.MessageProto;
import com.nf.util.ProtoUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ClientHandler extends SimpleChannelInboundHandler<DataProto.Data> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DataProto.Data data) {
        if (data == null) {
            return;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        DataProto.Data.Builder dataBuilder = DataProto.Data.newBuilder();
        dataBuilder.setCode(1);
        dataBuilder.setUid("ox111-110");

        MessageProto.Book.Builder bb = MessageProto.Book.newBuilder();
        bb.setId(11);
        bb.setName("netty从入门到放弃");
        bb.setPrice(222);


        MessageProto.Person.Builder builder = MessageProto.Person.newBuilder();
        builder.setId(1);
        builder.setName("haha anly");
        builder.setAge(22);

        MessageProto.Person person = builder.build();

        bb.setAuth(person);

        MessageProto.Book book = bb.build();

        dataBuilder.setData(ProtoUtil.bytes2String(book.toByteArray()));

        ctx.writeAndFlush(dataBuilder.build());
    }
}
