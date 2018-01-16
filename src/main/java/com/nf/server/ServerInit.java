package com.nf.server;

import com.nf.service.ServiceManager;

public class ServerInit {

    private ServerInit() {
    }

    private static ServerInit instance = new ServerInit();

    public static ServerInit getInstance() {
        return instance;
    }

    public void initDispatcher() {
        new Thread(HandlerDispatcher.getInstance()).start();
    }

    public void initService() {
        ServiceManager.getInstance().init();
    }

    public void init() {
        initDispatcher();
        initService();
    }
}
