package cn.com.codingce;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Rabbitmq02SpringbootApplicationTests {

    // 注入 rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直连
     */
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend("hello", "hello word");
    }

    /**
     * work
     */
    @Test
    void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work模型");
        }
    }

    /**
     * fanout
     */
    @Test
    void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "Fanout模型发送的消息");
    }

}
