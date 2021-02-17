package com.huang.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通过通道申明交换机
        // 参数1：交换机名称
        // 参数2：路由模式
        channel.exchangeDeclare("log_direct","direct");
        // 定义 route key
        String routeKey = "error";
        // 发送消息
        channel.basicPublish("log_direct",routeKey,null,
                ("这是direct模型发布的基于 route key:["+routeKey+"]").getBytes());

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(connection,channel);

    }
}
