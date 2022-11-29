package cn.com.codingce.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xzMa
 * 启动类
 *
 * Eureka客户端
 */
@SpringBootApplication
@EnableEurekaClient //开启Eureka 在服务启动后, 自动注册到Eureka中
@EnableDiscoveryClient  //服务发现
public class DeptProvider8003 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8003.class, args);
    }
}
