package com.nf.netty;

import com.nf.server.ServerHanlder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("decoder", new NettyMessageDecoder())// 解码器
				.addLast("encoder", new NettyMessageEncoder())// 编码器
				.addLast("handler", new ServerHanlder());

	}

}
