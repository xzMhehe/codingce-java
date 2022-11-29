package cn.com.codingce.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {   //configuration -- spring applicationContext.xml

    //配置负载均衡实现RestTemplate  @LoadBalanced
    //IRule
    //AvailabilityFilteringRule: 先会过滤掉, 跳闸, 访问故障服务器
    //RoundRobinRule    轮询
    //RandomRule    随机
    //RetryRule： 会按照轮询获取服务~ 如果服务获取失败, 则会在指定的时间内进行, 重试
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Bean
    public IRule myRule() {
        return new RandomRule();
    }

}
