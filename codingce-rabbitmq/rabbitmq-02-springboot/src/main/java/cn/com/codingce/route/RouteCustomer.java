package cn.com.codingce.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author mxz
 */
@Component
public class RouteCustomer {


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 绑定临时队列
                    exchange = @Exchange(value = "directs", type = "direct"), // 自定义交换机名称和类型
                    key = {"info", "error", "warn"}
            )
    })
    public void receivel(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 绑定临时队列
                    exchange = @Exchange(value = "directs", type = "direct"), // 自定义交换机名称和类型
                    key = {"error"}
            )
    })
    public void receivel2(String message) {
        System.out.println("message1 = " + message);
    }

}
