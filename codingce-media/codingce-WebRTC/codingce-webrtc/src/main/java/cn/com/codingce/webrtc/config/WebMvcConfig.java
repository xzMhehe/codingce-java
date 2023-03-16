package cn.com.codingce.webrtc.config;

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
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }
}
