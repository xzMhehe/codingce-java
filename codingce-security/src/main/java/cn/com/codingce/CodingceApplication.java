package cn.com.codingce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mxz
 */
@SpringBootApplication
@MapperScan("cn.com.codingce.mapper")
public class CodingceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingceApplication.class, args);
    }

}