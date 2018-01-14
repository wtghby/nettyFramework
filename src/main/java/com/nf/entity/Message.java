package com.nf.entity;

import io.netty.channel.Channel;

/**
 * 后台处理逻辑的核心实体类
 */
public class Message {
    private short code;// 储存命令码
    private byte[] data;// 存放实际数据,用于protobuf解码成对应message
    private Channel channel;// 当前玩家的channel

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
