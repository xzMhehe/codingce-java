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
public class CodingceRouting {
    // 交换机名称
    private static final String EXCHANGE_NAME = "codingce_exchange_routing";
    // 路由Key
    private static final String ROUTING_KEY1 = "routing1";
    private static final String ROUTING_KEY2 = "routing2";

    public static void main(String[] args) throws Exception {
        // 创建生产者
        Channel channel = CodingceHello.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String message = "ROUTING_KEY1 我是1的消息";
        String message2 = "ROUTING_KEY2 我是2的消息";
        // 比之前多了一个交换机名称
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY1, null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY2, null, message2.getBytes());
        log.info("CodingceRouting basicPublish done");
        /////////////////////////////////////////////////////////////////
        // 声明一个交换机，并设置其类型为"fanout"
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 声明一个队列并绑定交换机和路由Key
        String queueName = channel.queueDeclare("r1", false, false, false, null).getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY1);
        String queueName2 = channel.queueDeclare("r2", false, false, false, null).getQueue();
        channel.queueBind(queueName2, EXCHANGE_NAME, ROUTING_KEY1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("CodingceRouting r1 customer收到消息：{}", message);
            }
        };
        channel.basicConsume("r1", true, consumer);

        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("CodingceRouting r2 customer收到消息：{}", message);
            }
        };
        channel.basicConsume("r2", true, consumer2);
    }

}