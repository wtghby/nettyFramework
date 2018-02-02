package com.nf.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.nf.entity.Message;
import com.nf.proto.DataProto;
import com.nf.proto.MessageProto;
import com.nf.service.core.AbstractService;

public class HelloService extends AbstractService {

    public void service(Message message) {
        if (message == null) {
            System.out.println("message empty");
            return;
        }
        DataProto.Data data = message.getData();
        if (data.getCode() == 1) {
            System.out.println("HelloService " + data.getUid());
            try {
                MessageProto.Book book = MessageProto.Book.parseFrom(data.getData().toByteArray());
                System.out.println(book.getId() + "---" + book.getPrice() + "---" + book.getName());

                message.write(data);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
    }

}
