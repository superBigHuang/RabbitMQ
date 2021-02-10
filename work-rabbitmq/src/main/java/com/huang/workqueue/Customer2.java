package com.huang.workqueue;

import com.huang.helloworld.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者2
 */
public class Customer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取连接
        Channel channel = connection.createChannel();

        // 绑定队列
        channel.queueDeclare("work", true, false, false, null);

        channel.basicQos(1);
        // 获取消息
        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("customer-2:" + new String(body));
                // 参数1：确认的是队列中哪个具体的消息
                // 参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
