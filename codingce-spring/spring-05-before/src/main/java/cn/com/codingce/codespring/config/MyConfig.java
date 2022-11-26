package cn.com.codingce.codespring.config;

import cn.com.codingce.codespring.entity.MyEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 基于 Java 的配置
 * //@Configuration 和 @Bean 注解
 *
 * @author 2460798168@qq.com
 * @date 2019/12/26 16:22
 */
@Configuration
public class MyConfig {

    @Bean
    public MyEntity myEntity() {
        return new MyEntity();
    }

}
