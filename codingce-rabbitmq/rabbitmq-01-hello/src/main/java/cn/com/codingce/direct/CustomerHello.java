package cn.com.codingce.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author mxz
 */
@Component
public class CustomerHello {
    public static void main(String[] args) throws IOException, TimeoutException {
// 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/codingce");
        // 设置访问虚拟主机用户名密码
        connectionFactory.setUsername("codingce");
        connectionFactory.setPassword("123456");

        // 创建连接对象
        Connection connection = connectionFactory.newConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 通道绑定对象
        channel.queueDeclare("hello", false, false, false, null);

        // 消费消息
        // 参数1 消息队列的消息, 队列名称
        // 参数2 开启消息的确认机制
        // 参数3 消息时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            // 最后一个参数 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body)" + new String(body));
            }
        });

        channel.close();
        connection.close();
    }


    public void mqProvider() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/codingce");
        // 设置访问虚拟主机用户名密码
        connectionFactory.setUsername("codingce");
        connectionFactory.setPassword("123456");

        // 创建连接对象
        Connection connection = connectionFactory.newConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 通道绑定对象
        channel.queueDeclare("hello", false, false, false, null);

        // 消费消息
        // 参数1 消息队列的消息, 队列名称
        // 参数2 开启消息的确认机制
        // 参数3 消息时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            // 最后一个参数 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body)" + new String(body));
            }
        });

        channel.close();
        connection.close();

    }

}
