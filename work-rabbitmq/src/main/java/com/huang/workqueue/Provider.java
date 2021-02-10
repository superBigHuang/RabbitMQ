package com.huang.workqueue;

import com.huang.helloworld.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        // 通过通道申明队列
        channel.queueDeclare("work", true, false, false, null);

        // 发布消息
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "work", null, (i + "hello work queue").getBytes());
        }

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(connection, channel);


    }
}
