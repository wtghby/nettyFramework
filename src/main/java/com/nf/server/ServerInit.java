package com.nf.server;

import com.nf.service.core.ServiceManager;
import com.nf.util.ProReaderUtil;
import org.apache.log4j.PropertyConfigurator;

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
        initLog4j();
        initDispatcher();
        initService();
    }

    /**
     * 初始化log4j
     */
    public void initLog4j() {
        try {
            PropertyConfigurator.configure(ProReaderUtil.getInstance().getLog4jPro());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
