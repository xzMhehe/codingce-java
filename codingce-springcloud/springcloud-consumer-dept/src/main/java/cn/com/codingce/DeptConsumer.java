package cn.com.codingce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //Eureka 客户端
public class DeptConsumer {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer.class, args);
    }
}
