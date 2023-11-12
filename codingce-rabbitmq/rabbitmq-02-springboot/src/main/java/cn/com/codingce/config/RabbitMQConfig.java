package cn.com.codingce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 交换机名称
     */
    public static final String EXCHANGE_HRA3_NAME = "health_hra3_exchange";

    /**
     * 队列名称
     */
    public static final String HRA3_QUEUE = "health_hra3_queue";

    @Bean("healthHra3Exchange")
    public Exchange healthHra3Exchange() {
        // 创建交换机，durable代表持久化，使用Bean注入
        return ExchangeBuilder.topicExchange(EXCHANGE_HRA3_NAME).durable(true).build();
    }

    @Bean("healthHra3Queue")
    public Queue healthHra3Queue() {
        // 创建队列，使用Bean注入
        return QueueBuilder.durable(HRA3_QUEUE).build();
    }

    /**
     * 交换机和队列绑定关系
     *
     * @param queue    上面注入的队列Bean，如果你的项目又多个，记得给Bean取名字
     * @param exchange 上面注入的交换机Bean
     */
    @Bean
    public Binding healthHra3Binding(@Qualifier("healthHra3Queue") Queue queue, @Qualifier("healthHra3Exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("health.#").noargs();
    }

    // 第二个交换机、队列
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_INTERVENTION_NAME = "health_intervention_exchange";

    /**
     * 队列名称
     */
    public static final String INTERVENTION_QUEUE = "health_intervention_queue";

    @Bean("healthInterventionExchange")
    public Exchange healthInterventionExchange() {
        // 创建交换机，durable代表持久化，使用Bean注入
        return ExchangeBuilder.topicExchange(EXCHANGE_INTERVENTION_NAME).durable(true).build();
    }

    @Bean("healthInterventionQueue")
    public Queue healthInterventionQueue() {
        // 创建队列，使用Bean注入
        return QueueBuilder.durable(INTERVENTION_QUEUE).build();
    }

    /**
     * 交换机和队列绑定关系
     *
     * @param queue    上面注入的队列Bean，如果你的项目又多个，记得给Bean取名字
     * @param exchange 上面注入的交换机Bean
     */
    @Bean
    public Binding healthInterventionBinding(@Qualifier("healthInterventionQueue") Queue queue, @Qualifier("healthInterventionExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("intervention.#").noargs();
    }

}
