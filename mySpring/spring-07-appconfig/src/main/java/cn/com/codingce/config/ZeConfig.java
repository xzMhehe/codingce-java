package cn.com.codingce.config;

import cn.com.codingce.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Configuration加上这个注解就相当于beans
 * @author xzMa
 */
//这个也会被Spring托管, 注册到容器中, 因为它本来就是个@Component, @Configuration代表一个配置类, 就和我们之前看到的beans.xml
@Configuration
@ComponentScan("cn.com.codingce.pojo")
@Import(ZeConfig2.class)
public class ZeConfig {

    //注册一个bean, 就相当于我们之前写的一个bean标签
    //这个方法的名字, 就相当于bean标签中的id属性
    //这个方法返回值, 就相当于bean标签中的class属性
    @Bean
    public User getUser() {
        return new User();//就是返回要注入到bean的对象!
    }

}
