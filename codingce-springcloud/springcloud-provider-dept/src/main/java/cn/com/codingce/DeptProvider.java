package cn.com.codingce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xzMa
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient //开启Eureka 在服务启动后, 自动注册到Eureka中
public class DeptProvider {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider.class, args);
    }
}
