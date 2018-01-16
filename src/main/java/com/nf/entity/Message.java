package com.nf.entity;

import com.nf.proto.DataProto;
import com.nf.server.Connection;

/**
 * 后台处理逻辑的核心实体类
 */
public class Message {

    private DataProto.Data data;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public DataProto.Data getData() {
        return data;
    }

    public void setData(DataProto.Data data) {
        this.data = data;
    }
}
