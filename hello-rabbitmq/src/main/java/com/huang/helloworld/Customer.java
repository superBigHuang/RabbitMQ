package com.huang.helloworld;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 消费者
public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置连接rabbitmq的主机
        connectionFactory.setHost("47.92.241.169");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        // 创建连接对象
        Connection connection = connectionFactory.newConnection();

        // 创建通道
        Channel channel = connection.createChannel();
        // 通道绑定消息队列
        // 消费者和生产者的消息队列特性要完全一致
        channel.queueDeclare("hello", false, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel);

        // 消费消息
        // 参数1：消费哪个队列的消息
        // 参数2：是否开启消息的自动确认
        // 参数3：消费消息时的回调接口
        channel.basicConsume("hello",true, new DefaultConsumer(channel){
            // 参数4：消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body = " + new String(body));
            }
        });

        // 在消费者端不建议关闭连接，需要一直监听
//        channel.close();
//        connection.close();

    }

}
