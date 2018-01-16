package com.nf.server;

import com.google.protobuf.ByteString;
import com.nf.entity.Message;
import com.nf.proto.DataProto;
import com.nf.proto.MessageProto;
import com.nf.util.ProtoUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<DataProto.Data> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DataProto.Data message) throws Exception {
        if (message == null) {
            return;
        }
        channelHandlerContext.channel();
//        message.setChannel(channelHandlerContext.channel());

        int code = message.getCode();

        if (code == 1) {
            ByteString data = message.getData();
            System.out.println(data);
            MessageProto.Book book = MessageProto.Book.parseFrom(ProtoUtil.string2Bytes(data));

            System.out.println("book : id = " + book.getId() + " name = " + book.getName() + " age = " + book.getPrice());
            MessageProto.Person person = book.getAuth();
            System.out.println("auth of book : id = " + person.getId() + " name = " + person.getName() + " age = " + person.getAge());
//            MessageProto.Person person = MessageProto.Person.parseFrom(message.getData());
//            System.out.println("person : id = " + person.getId() + " name = " + person.getName() + " age = " + person.getAge());
        } else if (code == 2) {
            MessageProto.Book book = MessageProto.Book.parseFrom(message.getData());
            System.out.println("book : id = " + book.getId() + " name = " + book.getName() + " age = " + book.getPrice());
            MessageProto.Person person = book.getAuth();
            System.out.println("auth of book : id = " + person.getId() + " name = " + person.getName() + " age = " + person.getAge());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
