package cn.com.codingce.work;

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
 * 自动确认消费 autoAck true 12搭配测试
 * <p>
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

        // 通道绑定对象
        channel.queueDeclare("work", true, false, false, null);

        // 消费消息
        // 参数1 消息队列的消息, 队列名称
        // 参数2 开启消息的确认机制
        // 参数3 消息时的回调接口
        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            // 最后一个参数 消息队列中取出的消息
            // 默认分配是平均的
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1" + new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        channel.close();
//        connection.close();
    }

}
