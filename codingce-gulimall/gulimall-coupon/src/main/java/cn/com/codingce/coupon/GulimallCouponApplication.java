package cn.com.codingce.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 如何使用 nacos 作为配置中心, 统一管理配置
 * 引入依赖
 *         <dependency>
 *             <groupId>com.alibaba.cloud</groupId>
 *             <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *         </dependency>
 * 创建一个 bootstrap.proprties
 *   spring.application.name=gulimall-coupon
 *   # 指定 nacos 配置中心地址
 *   spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 *
 *
 * @author mxz
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.com.codingce.coupon.dao")
public class GulimallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class, args);
    }

}
