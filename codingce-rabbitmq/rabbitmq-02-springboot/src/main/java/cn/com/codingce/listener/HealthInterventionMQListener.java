package cn.com.codingce.listener;

import cn.com.codingce.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = RabbitMQConfig.INTERVENTION_QUEUE) // 监听的队列名称
public class HealthInterventionMQListener {

    @RabbitHandler
    public void InterventionReleaseCouponRecordAcknowledge(String body, Message message, Channel channel) throws IOException {
        log.info("releaseCouponRecordAcknowledge three into");
        long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag=" + msgTag);
        System.out.println("message=" + message.toString());
        System.out.println("body=" + body);

        // 成功确认，使用此回执方法后，消息会被 rabbitmq broker 删除
        // channel.basicAck(msgTag,false); // 正常返回ACK确认信息
        // channel.basicNack(msgTag,false,true); // 告诉broker，消息拒绝确认，最后一个true代表返回队列，为False代表丢弃
    }

}