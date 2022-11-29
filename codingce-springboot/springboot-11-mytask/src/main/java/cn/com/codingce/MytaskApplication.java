package cn.com.codingce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync    //开启异步注解功能
@EnableScheduling   //开启定时功能注解
@SpringBootApplication
public class MytaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MytaskApplication.class, args);
    }

}
