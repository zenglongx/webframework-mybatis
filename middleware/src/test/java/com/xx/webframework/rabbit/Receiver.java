package com.xx.webframework.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "sample")
    public void processMessage(String content){
        System.out.println("Received "+content);
    }
}
