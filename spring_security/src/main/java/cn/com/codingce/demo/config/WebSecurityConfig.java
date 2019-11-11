package cn.com.codingce.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 配置spring security(spring security官方文档给出的例子
 * 简单后面的繁琐
 * 我们洁简的应用spring security到我们的项目，然后却开启了非常强大的功能，
 * 下面的例子，在内存中配置了一个用户名为user,密码为password，
 * 并且拥有USER角色的用户。
 * 我们还是要探究一下，spring security到底为我们做了一些什么样的工作。
 * 我们的入手点只有两个,@EnableWebSecurity注解和WebSecurityConfigurerAdapter这个适配器类。
 *
 * @EnableWebSecurity 注解
 * 此处还是有必要简单翻译一下官方对于类的注释,以便于更加清楚的理解注解的作用
 * 注释中提到两个很重要的点，以及一个配置示例，
 *
 * 第一个点：@EnableWebSecurity配置到拥有注解 @Configuration的类上，就可以获取到spring security的支持.
 * 第二个点: WebSecurityConfigurer的子类可以扩展spring security的应用
 * 由此可知@EnableWebSecurity使我们拥有spring security的能力。
 * WebSecurityConfigurer 使我们能根据业务扩展我们的应用,
 * 而WebSecurityConfigurerAdapter是WebSecurityConfigurer 的一个适配器，必然也是做了很多默认的工作。
 *
 * 从以上可以稍微总结一下我们下一步需要探究的问题
 * WebSecurityConfigurerAdapter到底为我们做了什么工作.
 * 上面注解的源代码中Import了一个很重要的配置类WebSecurityConfiguration怎样组件我们的过滤器(或者说过滤器链)
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 15:55
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
