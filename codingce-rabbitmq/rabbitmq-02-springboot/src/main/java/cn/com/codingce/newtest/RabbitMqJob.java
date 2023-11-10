package cn.com.codingce.newtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

@Configuration
@EnableScheduling
public class RabbitMqJob {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqJob.class);

    @Autowired
    private Send send;

    //每30s发送一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMessage() {
        log.info("开始发送消息：" + Instant.now());
        try {
            send.sendMsg("hello world");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

