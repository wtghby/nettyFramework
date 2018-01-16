package com.nf.server;

import com.nf.proto.DataProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端连接类
 *
 * @author lee
 * 2015-1-30
 */
public class Connection {

    /**
     * 连接标识id
     */
    private String id;

    /**
     * 连接上下文
     */
    private ChannelHandlerContext ctx;

    private volatile boolean isClosed = false;

    /**
     * 是否是被踢下线
     */
    private boolean isKick = false;

    public Connection(String id, ChannelHandlerContext ctx) {
        this.id = id;
        this.ctx = ctx;
    }

    public String getId() {
        return id;
    }

    public Channel getChannel() {
        return ctx.channel();
    }

    /**
     * 往连接写入数据
     *
     * @param data
     */
    public void write(DataProto.Data data) {
        if (data == null)
            return;
        ctx.writeAndFlush(data);
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (!isClosed) {
            isClosed = true;
            ctx.close();
        }
    }

    public boolean isClosed() {
        return isClosed;
    }

    public boolean isKick() {
        return isKick;
    }

    public void setKick(boolean isKick) {
        this.isKick = isKick;
    }
}
