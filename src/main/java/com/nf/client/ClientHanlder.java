package com.nf.client;

import com.nf.entity.Message;
import com.nf.proto.MessageProto;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ClientHanlder extends SimpleChannelInboundHandler<Message> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        if (message == null) {
            return;
        }
        message.setChannel(channelHandlerContext.channel());

        // int playerid = ServerCache.get(ctx.channel());

        int csCommondCode = message.getCode();
//        if (csCommondCode < 100) {// 100以内暂时不用
//
//        } else if (csCommondCode >= 100 && csCommondCode < 200) { // 100-200用于注册
//            LoginQueue.getInstance().put(msg);
//
//        } else {// 消息可能较多,可以分几个队列,这里先放一个
//            CommonQueue.getInstance().put(msg);
//        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

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


        Message message = new Message();
        message.setCode((short) 2);
        message.setData(book.toByteArray());


        ctx.writeAndFlush(message);
    }
}
