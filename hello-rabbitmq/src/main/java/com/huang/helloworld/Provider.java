package com.huang.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    // 生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 通过连接，获取连接中的通道
        Channel channel = connection.createChannel();

        // 通道绑定消息队列
        // 参数1：队列名称 如果队列不存在会自动创建
        // 参数2：用来定义队列的特性是否要持久化
        //          如果没有持久化，重启服务后队列和内部的消息会丢失
        //          如果持久化了，虽然队列会保存但是消息还是会丢失
        // 参数3：是否独占队列 当前的队列只能当前的连接可用
        // 参数4：是否在消费完成后删除队列
        // 参数5：额外附加参数
        channel.queueDeclare("hello", false, false, false, null);

        // 发布消息
        // 可以往其他队列发送消息，并不是只能往hello发送
        // 参数1：交换机名称 参数2：队列名称  参数3：传递消息的额外设置  参数4：消息的内容
        // 参数3设置为 MessageProperties.PERSISTENT_TEXT_PLAIN 表示持久化消息
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());

        // 关闭连接
        RabbitMQUtils.closeConnectionAndChannel(connection,channel);
    }
}
