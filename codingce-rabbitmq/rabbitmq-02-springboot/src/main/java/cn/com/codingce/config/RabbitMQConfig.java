package cn.com.codingce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "health_exchange";

    /**
     * 队列名称
     */
    public static final String QUEUE = "health_hra3_queue";


    @Bean
    public Exchange orderExchange() {
        // 创建交换机，durable代表持久化，使用Bean注入
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Queue healthHra3Queue() {
        // 创建队列，使用Bean注入
        return QueueBuilder.durable(QUEUE).build();
    }

    /**
     * 交换机和队列绑定关系
     *
     * @param queue    上面注入的队列Bean，如果你的项目又多个，记得给Bean取名字
     * @param exchange 上面注入的交换机Bean
     */
    @Bean
    public Binding healthBinding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("health.#").noargs();
    }

}
