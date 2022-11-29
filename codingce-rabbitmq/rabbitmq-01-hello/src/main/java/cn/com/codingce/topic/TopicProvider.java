package cn.com.codingce.topic;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * <p>
 *
 * @author mxz
 */
@Component
public class TopicProvider {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();


        // 声明交换机以及交换机类型
        channel.exchangeDeclare("topics", "topic");


        // 路由key
        String routeKey = "user.save";

        channel.basicPublish("topics", routeKey, null, ("这里是 topic 动态路由模型, routeKey:[" + routeKey + "]").getBytes());

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
