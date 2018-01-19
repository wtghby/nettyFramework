package com.nf.test;


import com.nf.server.Bootstrap;
import com.nf.server.ServerInit;
import com.nf.util.ProReaderUtil;

/**
 * Created by Administrator on 2018/1/12 0012.
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        ServerInit.getInstance().init();
        Bootstrap server = new Bootstrap(9999);
        server.run();
    }
}
