package cn.com.codingce.webrtc.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Modifier;

/**
 * GsonConfig
 *
 * @author ma
 */
@Configuration(proxyBeanMethods = false)
public class GsonConfig {
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter() {

        // Spring提供了Gson的转换器对象
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        // 创建Gson的构建器，通过构建器设置Gson的相关设置
        GsonBuilder gsonBuilder = new GsonBuilder();
        // 设置Gson转化时间的格式
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // Gson解析时，它将会过滤被protected修饰的属性
        gsonBuilder.excludeFieldsWithModifiers(Modifier.PROTECTED);
        // 通过构建器创建Gson对象
        Gson gson = gsonBuilder.create();
        // 将设置好的Gson对象，传递给转化器
        converter.setGson(gson);

        return converter;
    }

}
