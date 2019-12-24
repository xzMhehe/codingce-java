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
 * =================================================================================================
 * spring security创建流程
 * 如果我们忽略掉细节，只看最重要的步骤，大概如此.下面我们对每一个步骤来做相应的源代码解释
 * |- WebSecurityConfiguration中的 setFilterChainProxySecurityConfigurer（）方法
 * |-- 其实我们根据debug获取到的来看，这个beansOfType 就是我们定义的继承自WebSecurityConfigurerAdapter的类，
 *     通过查看父类的定义，我们知道调用build()方法最后返回的必须是一个Filter对象，
 *     可以自行参考顶级父类(或接口)WebSecurityConfigurer和SecurityBuilder
 * |--- 这里直接用new 来创建一个WebSecurity的对象
 * |--- 当有多个配置项时进行排序
 * |--- 进行order重复验证
 * |--- 将配置信息配置到webSecurity中，变量configurers中会存储这个信息
 * |-WebSecurityConfiguration中的 springSecurityFilterChain（）方法
 * |-- 为我们创建了一个名字叫做springSecurityFilterChain的Filter
 * |-- 到此为止，已经建立了一个Filter对象，而这个Filter将会拦截掉我们的请求，
 *     对请求进行过滤拦截，从而起到对资源进行认证保护的作用。然后这个Filter并非我们自己平时定义的Filter这么简单,
 *     这个过滤器也只是一个代理的过滤器而已，里面还会有过滤器链.
 * =================================================================================================
 * WebSecurityConfigurerAdapter 为我们做了什么
 * |-还是从最重要的开始
 * |- 1.HttpSecurity 通过getHttp()获取，后面会详细说到这个类
 * |- 2.UserDetailsService 用户信息获取
 * |- 3.AuthenticationManager 认证管理类 后面会详细讲解到这些信息，包括这些信息在过滤其中起到什么作用，然后最重要的是，我们要先理清楚过滤器的机制，下一篇会详细讲过滤器链
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
