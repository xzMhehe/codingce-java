package cn.com.codingce.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodingCeRule {

    @Bean
    public IRule myRule() {
        //return new CodingCeRandomRule();
        //默认是轮询 现在我们定义为 CodingCeRandomRule
        // 本次自定义   频繁操作 会出现 500 错误 继续自定义写RetryRule

        return new RandomRule();
    }

}
