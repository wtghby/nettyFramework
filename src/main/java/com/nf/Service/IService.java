package com.nf.Service;

import com.nf.entity.Message;
import io.netty.channel.ChannelHandlerContext;

public interface IService {

    void onConnected(ChannelHandlerContext context);

    void onRead(Message message);

    void onError(ChannelHandlerContext context, Throwable cause);
}
