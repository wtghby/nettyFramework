package com.nf.server;

import com.nf.entity.Message;
import com.nf.proto.MessageProto;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ServerHanlder extends SimpleChannelInboundHandler<Message> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        if (message == null) {
            return;
        }
        message.setChannel(channelHandlerContext.channel());

        // int playerid = ServerCache.get(ctx.channel());

        int code = message.getCode();
        if (code == 1) {
            MessageProto.Person person = MessageProto.Person.parseFrom(message.getData());
            System.out.println("person : id = " + person.getId() + " name = " + person.getName() + " age = " + person.getAge());
        } else if (code == 2) {
            MessageProto.Book book = MessageProto.Book.parseFrom(message.getData());
            System.out.println("book : id = " + book.getId() + " name = " + book.getName() + " age = " + book.getPrice());
            MessageProto.Person person = book.getAuth();
            System.out.println("auth of book : id = " + person.getId() + " name = " + person.getName() + " age = " + person.getAge());
        }
//        if (csCommondCode < 100) {// 100以内暂时不用
//
//        } else if (csCommondCode >= 100 && csCommondCode < 200) { // 100-200用于注册
//            LoginQueue.getInstance().put(msg);
//
//        } else {// 消息可能较多,可以分几个队列,这里先放一个
//            CommonQueue.getInstance().put(msg);
//        }
    }
}
