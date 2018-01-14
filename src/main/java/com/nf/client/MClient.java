package com.nf.client;

import com.nf.netty.NettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2018/1/13 0013.
 */
public class MClient {
    public void connect(String host, int port) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(workerGroup);
            boot.channel(NioSocketChannel.class);
            boot.remoteAddress(host, port);
            boot.option(ChannelOption.SO_KEEPALIVE, true);
            boot.handler(new NettyClientInitializer());

            ChannelFuture cf = boot.connect().sync();
            cf.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
