package cn.com.codingce.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //EnableEurekaServer
public class Eureka_Config_Server_7001 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_Config_Server_7001.class, args);
    }
}
