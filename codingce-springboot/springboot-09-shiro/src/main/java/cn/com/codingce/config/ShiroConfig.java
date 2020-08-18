package cn.com.codingce.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactroyBean    第三步
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager webSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);





        return shiroFilterFactoryBean;
    }

    //DafaultWebSecurityManager 第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);

        return securityManager;

    }

    //创建realm对象,    需要自定义   这是第一步       ||    @Bean(name = "UserRealm")   互联  @Qualifier("userRealm")   与方法名一致  前面的@Bean就不用写name属性
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
