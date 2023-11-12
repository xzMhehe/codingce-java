package cn.com.codingce.listener;

import cn.com.codingce.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = RabbitMQConfig.QUEUE) // 监听的队列名称
public class healthHra3MQListener {

    /**
     * RabbitHandler会自动匹配消息类型（消息自动确认）
     *
     * @param msg     发送的是String类型，这里用String进行接收，RabbitHandler会自动进行匹配
     * @param message
     * @throws IOException
     */
    @RabbitHandler
    public void releaseCouponRecord(String msg, Message message) throws IOException {
        log.info("releaseCouponRecord into"); // 监听到消息：消息内容，msg=新HRA3报告来啦！！
        long msgTag = message.getMessageProperties().getDeliveryTag();
        log.info("监听到消息：消息内容，msg={}", msg); // 监听到消息：消息内容，msg=新HRA3报告来啦！！
        log.info("msgTag={}", msgTag); // msgTag=1
        log.info("message={}", message.toString()); // message=(Body:'新HRA3报告来啦！！' MessageProperties [headers={}, ……
    }

}