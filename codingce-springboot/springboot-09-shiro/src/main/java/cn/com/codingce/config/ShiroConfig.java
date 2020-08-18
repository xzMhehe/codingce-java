package cn.com.codingce.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactroyBean    第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager webSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(webSecurityManager);
        //添加shiro 的内置过滤器
        /**
         * anno 无需认证就可以访问
         * authc: 必须认证才能访问
         * user:    必须拥有记住我功能才能使用
         * perm:    拥有对某个资源的权限才能访问
         * role:    拥有某个角色权限才能访问
         */
        HashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add", "authc");
        filterMap.put("/user/update", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
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
