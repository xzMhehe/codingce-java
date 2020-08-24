package cn.com.codingce.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    //在一个特定的时间执行这样的
    //秒 分 时 日 月   周几
    //12 37 18 * * ？ 每天的10点15分30  执行一次
    //30 0/5 18,10 * * ？ 每天的10点18点  每隔五分钟执行一次
    //0 15 10 ？ * 1-6 每个月周一到周六10点15分钟执行一次
    @Scheduled(cron = "12 37 18 * * ？")
    public void hello() {
        System.out.println("Hello, 你被执行了");
    }

}
