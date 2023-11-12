package cn.com.codingce.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CodingcePubSub {

    // 交换机名称
    private static final String EXCHANGE_NAME = "codingce_exchange";

    // 路由Key
    private static final String ROUTING_KEY = "codingce_key";

    public static void main(String[] args) throws Exception {
        // 创建生产者
        Channel channel = CodingceHello.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String message = "Hello World EXCHANGE_NAME ROUTING_KEY !";
        // 比之前多了一个交换机名称，发送6条消息方便测试
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        log.info("CodingcePubSub basicPublish done");
        ////////////////////////////////////////////////
        // 声明一个交换机，并设置其类型为"fanout"
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 声明一个队列并绑定交换机和路由Key
        String queueName = channel.queueDeclare("q1", false, false, false, null).getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
        String queueName2 = channel.queueDeclare("q2", false, false, false, null).getQueue();
        channel.queueBind(queueName2, EXCHANGE_NAME, ROUTING_KEY);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("CodingcePubSub Consumer handleDelivery q1 customer收到消息：{}", message);
            }
        };
        Consumer consumer1 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("CodingcePubSub Consumer handleDelivery q11 1customer收到消息：{}", message);
            }
        };
        // q1队列有两个消费者，但每次只会有一个q1的消费者收到消息
        channel.basicConsume("q1", true, consumer);
        channel.basicConsume("q1", true, consumer1);

        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("CodingcePubSub Consumer handleDelivery q2 customer收到消息：{}", message);
            }
        };
        // q2只绑定一个消费者，所以这个消费者100%会收到消息。
        channel.basicConsume("q2", true, consumer2);
    }

}
