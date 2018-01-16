package com.nf.service.core;

import com.nf.entity.Message;

public interface IService extends Runnable {

    void pushMessage(Message message);

    void service(Message message);
}
