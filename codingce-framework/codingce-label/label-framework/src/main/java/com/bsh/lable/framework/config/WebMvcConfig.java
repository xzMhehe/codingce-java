package com.bsh.lable.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Mvc config
 *
 * @author ma
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置本地文件映射到url上
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * 重写方法 修改tomcat 虚拟映射
         * 指的是对外暴露的访问路径 addResourceHandler
         * registry.addResourceHandler("/static/**") http://localhost:8091/static/favicon.ico
         * 对外 registry.addResourceHandler("/**") http://localhost:8091/favicon.ico
         * 指的是内部文件放置的目录 addResourceLocations
         */
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        /**
         * swagger
         */
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * knife4j
         */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * webjars
         */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


//    /**
//     * 接口地址忽略大小写
//     *
//     * @param configurer configurer
//     */
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        AntPathMatcher matcher = new AntPathMatcher();
//        matcher.setCaseSensitive(false);
//        configurer.setPathMatcher(matcher);
//    }

}
