package com.nf.service;

import com.nf.constant.Config;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private static ServiceManager instance;

    /**
     * 业务逻辑集合
     */
    private Map<String, IService> serviceMap = new HashMap<String, IService>();

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }

        return instance;
    }

    /**
     * 注册业务逻辑
     *
     * @param cls
     * @param module
     * @throws Exception
     */
    public void register(String module, IService cls) {
        startService(cls);
        serviceMap.put(module, cls);
    }

    public IService getService(String module) {
        return serviceMap.get(module);
    }

    private void startService(IService service) {
        new Thread(service).start();
    }

    public void init() {
        register(Config.KEY_M1, new HelloService());
    }
}
