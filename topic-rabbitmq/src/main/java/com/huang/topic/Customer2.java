package com.huang.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.TimeoutException;

public class Customer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics", "topic");

        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();

        // 绑定队列和交换机
        // * 只能匹配1个单词
        channel.queueBind(queue, "topics", "user.*");

        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
