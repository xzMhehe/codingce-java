package cn.com.codingce.fanout;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 消费者 3
 * <p>
 * 任务模型 fanout
 *
 * @author mxz
 */
public class CustomerThree {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        // 通道绑定交换机
        channel.exchangeDeclare("logs", "fanout");

        // 临时队列
        String queue = channel.queueDeclare().getQueue();

        // 绑定交换机队列
        channel.queueBind(queue, "logs", "");

        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3 " + new String(body));
            }
        });

    }

}
