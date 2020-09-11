package cn.com.codingce.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {   //configuration -- spring applicationContext.xml
    //配置负载均衡实现 RestTemplate    @LoadBalanced
    @Bean
    @LoadBalanced   //Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }




}
