package com.nf.service;

import com.nf.entity.Message;
import com.nf.proto.DataProto;

public interface IService extends Runnable {

    void pushMessage(Message message);

    void service(Message message);
}
