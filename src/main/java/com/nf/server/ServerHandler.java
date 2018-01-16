package com.nf.server;

import com.nf.entity.Message;
import com.nf.proto.DataProto;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<DataProto.Data> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DataProto.Data data) throws Exception {
        if (data == null) {
            return;
        }

        Message message = new Message();
        message.setData(data);
        Connection connection = ConnectionManager.getInstance().getConnection(channelHandlerContext);
        message.setConnection(connection);

        HandlerDispatcher.getInstance().pushMessage(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ConnectionManager.getInstance().addConnection(ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ConnectionManager.getInstance().removeConnection(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

}
