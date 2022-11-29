package cn.com.codingce.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xzMa
 * 在微服务启动的时候就能加载我们自定义的Ribbon类
 */
@SpringBootApplication
@EnableEurekaClient //Eureka 客户端
@EnableFeignClients(basePackages = {"cn.com.codingce"})
@ComponentScan("cn.com.codingce")
public class FeginDeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeginDeptConsumer_80.class, args);
    }
}
