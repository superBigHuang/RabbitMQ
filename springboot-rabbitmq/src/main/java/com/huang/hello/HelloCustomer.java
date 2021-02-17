package com.huang.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
// 消费者监听hello队列
// queuesToDeclare 没有队列就创建一个队列
// 设置队列的参数需要在消费者这一端设置
@RabbitListener(queuesToDeclare = @Queue(value = "hello", durable = "false"))
public class HelloCustomer {

    // 拿到消息后的回调函数
    @RabbitHandler
    public void receivel(String message) {
        System.out.println("message = " + message);
    }
}
