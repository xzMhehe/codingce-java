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
 * 能者多劳  34 搭配测试
 * <p>
 * 消费者 3
 *
 * @author mxz
 */
@Component
public class CustomerThree {
    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 每一次只能消费一个消息
        channel.basicQos(1);
        // 通道绑定对象
        channel.queueDeclare("work", true, false, false, null);

        // 参数1 队列名称 参数2(autoAck) 消息自动确认 true 消费者自动向 rabbitMQ 确认消息消费  false 不会自动确认消息
        // 若出现消费者宕机情况 消费者三可以进行消费
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            // 最后一个参数 消息队列中取出的消息
            // 默认分配是平均的
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1" + new String(body));
                // 手动确认 参数1 确认队列中
                channel.basicAck(envelope.getDeliveryTag(), false);
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
