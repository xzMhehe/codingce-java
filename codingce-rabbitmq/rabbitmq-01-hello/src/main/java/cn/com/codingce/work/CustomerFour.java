package cn.com.codingce.work;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 能者多劳  34 搭配测试
 * <p>
 * 消费者 4
 *
 * @author mxz
 */
@Component
public class CustomerFour {
    public static void main(String[] args) throws IOException {

        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 每一次只能消费一个消息
        channel.basicQos(1);

        // 通道绑定对象
        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            // 最后一个参数 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1" + new String(body));

                // 手动确认 参数1 手动确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

//        channel.close();
//        connection.close();
    }

}
