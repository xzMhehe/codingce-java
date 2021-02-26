package cn.com.codingce.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 库存系统
 *
 * @author mxz
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "cn.com.codingce.ware.feign")
@MapperScan("cn.com.codingce.ware.dao")
public class GulimallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallWareApplication.class, args);
    }

}
