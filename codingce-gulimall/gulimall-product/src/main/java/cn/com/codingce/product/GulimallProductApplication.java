package cn.com.codingce.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 整合Mybatis-Plus
 *  1 导入依赖
 *  2 配置
 *      配置数据源
 *          导入数据库驱动
 *          在application.yml 配置数据源相关信息
 *      配置Mybatis-Plus
 *          使用@mapperScan
 *          告诉Mybats-Plus sql 映射文件
 *
 *
 * @author mxz
 */
@SpringBootApplication
@MapperScan("cn.com.codingce.product.dao")
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
