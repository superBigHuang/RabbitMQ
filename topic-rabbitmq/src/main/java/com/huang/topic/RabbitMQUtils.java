package com.huang.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    // 将工厂抽取出来改为单例
    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        // 设置连接rabbitmq的主机
        connectionFactory.setHost("47.92.241.169");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }


    // 获得连接
    public static Connection getConnection() throws IOException, TimeoutException {

        // 创建连接对象
        return connectionFactory.newConnection();
    }

    public static void closeConnectionAndChannel(Connection connection, Channel channel) {
        try {
            if (connection != null && channel != null) {
                channel.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
