package cn.com.codingce.customer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mxz
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloCustomer {

    /**
     * @param message
     */
    @RabbitHandler
    public void receivel(String message) {
        System.out.println("message: " + message);
    }

}
