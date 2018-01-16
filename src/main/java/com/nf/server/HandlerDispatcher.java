package com.nf.server;

import com.nf.constant.Config;
import com.nf.entity.Message;
import com.nf.service.IService;
import com.nf.service.ServiceManager;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 消息分发类，分发netty的所有客户端消息
 */
public class HandlerDispatcher implements Runnable {

    private static HandlerDispatcher instance;
    /**
     * 线程运行标志
     */
    private volatile boolean isRunning = true;
    /**
     * 消息队列
     */
    Queue<Message> messagesQueue = new ConcurrentLinkedQueue<Message>();

    private HandlerDispatcher() {
    }

    public static HandlerDispatcher getInstance() {
        if (instance == null) {
            instance = new HandlerDispatcher();
        }

        return instance;
    }

    /**
     * 新消息放到队列
     *
     * @param message
     */
    public void pushMessage(Message message) {
        if (isRunning) {
            synchronized (messagesQueue) {
                messagesQueue.add(message);
                messagesQueue.notifyAll();
            }
        }
    }

    /**
     * 队列消息是否处理完毕
     *
     * @return
     */
    public boolean isDone() {
        return messagesQueue.isEmpty();
    }


    public void run() {
        while (true) {
            synchronized (messagesQueue) {
                if (isDone()) {
                    try {
                        System.out.println("messagesQueue empty");
                        messagesQueue.wait();
                    } catch (InterruptedException e) {
                        if (!isRunning) {
                            return;
                        }
                    }
                }
            }

            Message messages = null;
            while ((messages = messagesQueue.poll()) != null) {
                int code = messages.getData().getCode();
                if (code < 0)
                    return;
                IService service = null;

                if (code < Config.MODULE_E_1) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M1);
                } else if (code < Config.MODULE_E_2) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M2);
                } else if (code < Config.MODULE_E_3) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M3);
                } else if (code < Config.MODULE_E_4) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M4);
                } else if (code < Config.MODULE_E_5) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M5);
                } else if (code < Config.MODULE_E_6) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M6);
                } else if (code < Config.MODULE_E_7) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M7);
                } else if (code < Config.MODULE_E_8) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M8);
                } else if (code < Config.MODULE_E_9) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M9);
                } else if (code < Config.MODULE_E_10) {
                    service = ServiceManager.getInstance().getService(Config.KEY_M10);
                }

                if (service != null) {
                    service.pushMessage(messages);
                }
            }
        }
    }
}
