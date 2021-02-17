package com.huang.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// 仅实现轮询，如果要实现能者多劳还要另外加配置
public class WorkCustomer {

    // 一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel1(String message) {
        System.out.println("message1 = " + message);
    }
    // 一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel2(String message) {
        System.out.println("message2 = " + message);
    }

}
