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

    @GetMapping(value = "/default", produces = "text/html;charset=utf-8")
    public String getDefault() {
        return "队列服务运行正常...";
    }

}
