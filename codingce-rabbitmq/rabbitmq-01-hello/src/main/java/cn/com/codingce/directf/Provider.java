package cn.com.codingce.directf;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author mxz
 */
public class Provider {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        // 通过通道声明交换机   参数1 交换机名称  参数2 路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        // 发送消息
        String routingKey = "error";

        channel.basicPublish("logs_direct", routingKey, null, ("这是 direct 模式发布基于 route_key [" + routingKey + "]").getBytes());

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
