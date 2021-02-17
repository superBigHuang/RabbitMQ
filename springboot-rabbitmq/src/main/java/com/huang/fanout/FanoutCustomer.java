package com.huang.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 不指定名字代表创建临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")   // 要绑定的指定的交换机
            )
    })
    public void receivel1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 不指定名字代表创建临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")   // 要绑定的指定的交换机
            )
    })
    public void receivel2(String message) {
        System.out.println("message2 = " + message);
    }
}
