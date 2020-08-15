package cn.com.codingce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//扩展springmvc
//如果要扩展springmvc, 官方建议这样去做
@Configuration
@EnableWebMvc   //这个注解就是导入了一个类, DelegatingWebMvcConfiguration: 从容器中获取所有的WebMvcconfig
public class MyMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//    }
//
//    @Bean
//    public ViewResolver myViewResolver() {
//        return new MyViewResolver();
//    }
//
//    //自定义一个自己的视图解析器MyViewResolver
//    public static class MyViewResolver implements ViewResolver {
//
//        @Override
//        public View resolveViewName(String s, Locale locale) throws Exception {
//            return null;
//        }
//    }


//    //视图跳转
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//
//        registry.addViewController("/xin").setViewName("test");
//
//    }


    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");

    }

    /**
     * SpringBoot 2.x 要重写该方法，不然 css、js、image 等静态资源路径无法访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
    }

    //自定义的国际化组件就生效了， 只有注入才能生效
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //添加拦截器                 excludePathPatterns不拦截名单
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
                //不需要拦截的请求
                .excludePathPatterns("/index.html", "/user/login", "/", "/css/*", "/js/**", "/img/**");
    }
}
