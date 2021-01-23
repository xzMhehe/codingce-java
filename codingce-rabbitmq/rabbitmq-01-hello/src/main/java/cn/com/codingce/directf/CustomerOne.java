package cn.com.codingce.directf;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 1
 *
 * @author mxz
 */
@Component
public class CustomerOne {
    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        // 基于 route_key 绑定队列交换机
        channel.queueBind(queue, "logs_direct", "error");

        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1： " + new String(body));
            }
        });

//        channel.close();
//        connection.close();
    }

}
