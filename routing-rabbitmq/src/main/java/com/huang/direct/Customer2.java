package com.huang.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道申明交换机及类型
        channel.exchangeDeclare("log_direct", "direct");
        // 创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        // 基于route key 绑定队列和交换机
        channel.queueBind(queue, "log_direct", "info");
        channel.queueBind(queue, "log_direct", "waring");
        channel.queueBind(queue, "log_direct", "error");

        // 获取消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });
    }
}
