package com.nf.test;


import com.nf.client.MClient;

/**
 * Created by Administrator on 2018/1/13 0013.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        MClient client = new MClient();
        client.connect("127.0.0.1", 9999);
    }
}
