package cn.com.codingce.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * fanout
 *
 * @author mxz
 */
@Component
@Slf4j
public class FanoutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")     // 绑定的交换机
            )
    })
    public void receivel(String message) {
        log.info("FanoutCustomer receivel message:{}", message);
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")     // 绑定的交换机
            )
    })
    public void receive2(String message) {
        log.info("FanoutCustomer receive2 message:{}", message);
        System.out.println("message2 = " + message);
    }

}
