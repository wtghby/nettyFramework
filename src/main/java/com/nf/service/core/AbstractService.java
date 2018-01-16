package com.nf.service.core;

import com.nf.entity.Message;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractService implements IService {

    /**
     * 线程运行标志
     */
    private volatile boolean isRunning = true;
    /**
     * 消息队列
     */
    Queue<Message> messagesQueue = new ConcurrentLinkedQueue<Message>();

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
                        System.out.println(this.getClass().getName() + " messagesQueue empty");
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
                service(messages);
            }
        }
    }
}
