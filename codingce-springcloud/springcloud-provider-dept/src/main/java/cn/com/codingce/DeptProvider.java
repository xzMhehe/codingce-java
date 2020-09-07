package cn.com.codingce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xzMa
 * 启动类
 */
@SpringBootApplication
@MapperScan("cn.com.codingce.dao")
public class DeptProvider {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider.class, args);
    }
}
