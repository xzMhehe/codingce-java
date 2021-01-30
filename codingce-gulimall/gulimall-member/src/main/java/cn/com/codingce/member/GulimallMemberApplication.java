package cn.com.codingce.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 要想使用远程调用服务
 *  1 引入open-feign
 *  2 编写一个接口, 告诉SpringCloud这个接口要调用远程服务
 *      1 声明接口的每一个方法都是调用哪个远程服务的那个请求
 *  3 开启远程调用功能  @EnableFeignClients(basePackages = "cn.com.codingce.member.feign")
 *
 * @author mxz
 */
@EnableFeignClients(basePackages = "cn.com.codingce.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.com.codingce.member.dao")
public class GulimallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class, args);
    }

}
