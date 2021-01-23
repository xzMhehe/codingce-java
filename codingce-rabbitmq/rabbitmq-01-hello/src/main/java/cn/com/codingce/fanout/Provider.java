package cn.com.codingce.fanout;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * <p>
 * 任务模型 fanout
 *
 * @author mxz
 */
@Component
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();


        // 将通道声明指定交换机  参数1 交换机名称   参数2 代表交换机类型 fanout 广播类型
        channel.exchangeDeclare("logs", "fanout");

        // 发送消息
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);

    }
}
