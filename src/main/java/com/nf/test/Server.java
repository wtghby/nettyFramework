package com.nf.test;


import com.nf.server.MServer;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        MServer server = new MServer(9999);
        server.run();
    }
}
