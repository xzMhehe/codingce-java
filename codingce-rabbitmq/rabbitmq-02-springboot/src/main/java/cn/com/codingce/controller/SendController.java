package cn.com.codingce.controller;

import cn.com.codingce.common.utils.R;
import cn.com.codingce.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class SendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/testSend")
    public R testSend() {
        log.info("testSend");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "health.#", "新HRA3报告来了！！");
        return R.ok();
    }

    /**
     * 可靠性投递confirmCallback
     *
     * @return
     */
    @GetMapping("/confirmCallback")
    public R confirmCallback() {
        log.info("生产者到交换机通过confirmCallback 可靠性投递 confirmCallback");
         /*
          correlationData：配置
          ack：交换机是否收到消息，true是成功，false是失败
          cause：失败的原因
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("confirm==== ack={}", ack);
            log.info("confirm==== cause={}", cause);
            if (ack) {
                log.info("发送成功，{}", cause);
            } else {
                log.error("发送失败，{}", cause);
            }
        });
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "health.new", "新HRA3报告来了！！");
        return R.ok();
    }

    @GetMapping("/returnCallback")
    public R returnCallback() {
        log.info("交换机到队列通过returnCallback 可靠性投递 returnCallback");
        // 为true,则交换机处理消息到路由失败，则会返回给生产者，开启强制消息投递（mandatory为设置为true），但消息未被路由至任何一个queue，则回退一条消息
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            int code = returnedMessage.getReplyCode();
            log.info("returnCallback code={}", code);
            log.info("returnCallback returned={}", returnedMessage);
        });
        // 这个routingKey是不存在的，它找不到这个路由，所以会出现异常从而触发上面的回调方法
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "xxx.health.new", "新HRA3报告来了！！");
        return R.ok();
    }

    @GetMapping(value = "/default", produces = "text/html;charset=utf-8")
    public String getDefault() {
        return "队列服务运行正常...";
    }

}
