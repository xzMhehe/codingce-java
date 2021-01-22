package cn.com.codingce.work;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * <p>
 * 任务模型 work quenue
 *
 * @author mxz
 */
@Component
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);

        for (int i = 0; i < 10; i++) {
            // 生产消息
            channel.basicPublish("", "work", null, (" " + i + "work quenue").getBytes());
        }

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);

    }
}
