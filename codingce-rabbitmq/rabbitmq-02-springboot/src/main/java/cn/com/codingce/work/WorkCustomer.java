package cn.com.codingce.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 第二种模型 work 模型
 *
 * @author mxz
 */
@Component
public class WorkCustomer {
    /**
     * 第1个消费者
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel(String message) {
        System.out.println("message1 = " + message);
    }

    /**
     * 第2个消费者
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel2(String message) {
        System.out.println("message2 = " + message);
    }
}
