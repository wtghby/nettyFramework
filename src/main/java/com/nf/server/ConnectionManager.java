package com.nf.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接管理器类
 *
 * @author lee
 * 2015-1-30
 */
public class ConnectionManager {

    /**
     * 连接存储集合
     */
    private Map<String, Connection> connections = new ConcurrentHashMap<String, Connection>();

    private static final ConnectionManager instance = new ConnectionManager();

    public static final ConnectionManager getInstance() {
        return instance;
    }

    /**
     * 添加一个连接到集合
     *
     * @param c
     */
    public void addConnection(Connection c) {
        connections.put(c.getId(), c);
    }

    /**
     * 根据连接上下文添加一个连接到集合
     *
     * @param context
     */
    public Connection addConnection(ChannelHandlerContext context) {
        final Connection c = new Connection(context.channel().id().asLongText(), context);
        connections.put(c.getId(), c);
        return c;
    }

    /**
     * 从集合中获取一个连接
     *
     * @param id
     * @return
     */
    public Connection getConnection(String id) {
        return connections.get(id);
    }

    public Connection getConnection(ChannelHandlerContext context) {
        return connections.get(context.channel().id().asLongText());
    }

    /**
     * 从集合中删除一个连接
     *
     * @param c
     */
    public void removeConnection(Connection c) {
        if (c == null)
            return;
        Connection conn = connections.remove(c.getId());
        conn.close();
    }

    public void removeConnection(ChannelHandlerContext context) {
        Connection connection = getConnection(context);
        removeConnection(connection);
    }
}
