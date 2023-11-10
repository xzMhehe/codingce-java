package cn.com.codingce.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅模型
 *
 * @author mxz
 */
@Component
@Slf4j
public class TopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(type = "topic", name = "topics"),
                    key = {"user.save", "user.*"}
            )
    }
    )
    public void receivel(String message) {
        log.info("RouteCustomer receivel message:{}", message);
        System.out.println("message1" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(type = "topic", name = "topics"),
                    key = {"produce.#", "order.#"}
            )
    }
    )
    public void receivel2(String message) {
        log.info("RouteCustomer receivel2 message:{}", message);
        System.out.println("message2" + message);
    }
}
