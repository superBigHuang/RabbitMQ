package com.huang;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRabbitMQ {

    // 注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello","hello world 4 springboot");
    }

    @Test
    public void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work 模型");
        }
    }

    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs","","Fanout 模型发送的消息");
    }

    @Test
    public void testRouter() {
        rabbitTemplate.convertAndSend("directs","error","发送的info信息");
    }

    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("topics","user.save.a.a","user.save 信息");
    }

}
