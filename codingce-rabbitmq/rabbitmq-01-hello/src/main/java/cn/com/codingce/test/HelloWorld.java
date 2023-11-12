package cn.com.codingce.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HelloWorld {

    // 队列名称
    private final static String QUEUE_NAME = "codingceHelloWorld";

    public static void main(String[] args) throws Exception {
        //  创建生产者
        Channel channel = getChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        // 发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        ///////////////////////////////////////////////////////////
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.printf("customer1收到消息：%s%n", message);
            }
        };
        // 创建消费者
        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.printf("customer2收到消息：%s%n", message);
            }
        };
        // 监听队列并消费消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
        channel.basicConsume(QUEUE_NAME, true, consumer2);
    }

    /**
     * 获取RabbitMQ连接对象
     */
    private static Channel getChannel() throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建通道
        return connection.createChannel();
    }
}
