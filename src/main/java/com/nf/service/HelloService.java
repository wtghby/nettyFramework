package com.nf.service;

import com.nf.entity.Message;
import com.nf.proto.DataProto;

public class HelloService extends AbstractService {

    public void service(Message message) {
        if (message == null) {
            System.out.println("message empty");
            return;
        }
        DataProto.Data data = message.getData();
        if (data.getCode() == 1) {
            System.out.println("HelloService " + data.getUid());
        }
    }

}
