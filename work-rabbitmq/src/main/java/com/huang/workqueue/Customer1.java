package com.huang.workqueue;

import com.huang.helloworld.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者1
 */
public class Customer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取连接
        Channel channel = connection.createChannel();

        // 绑定队列
        channel.queueDeclare("work", true, false, false, null);

        // 每次只能消费一个消息
        channel.basicQos(1);
        // 获取消息
        // 参数2：如果MQ给了五个消息，消费者会一次性全部确认消息已经消费了，如果消费者在消费消息的过程中发生宕机就会产生消息丢失，
        // 所以推荐该参数使用false
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("customer-1:" + new String(body));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 参数1：确认的是队列中哪个具体的消息
                // 参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
