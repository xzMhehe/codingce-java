package cn.com.codingce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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


    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/xin").setViewName("test");

    }
}
