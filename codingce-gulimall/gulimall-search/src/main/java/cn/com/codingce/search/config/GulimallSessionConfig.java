//package cn.com.codingce.search.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
///**
// * @author mxz
// * @Description: springSession配置类
// * @email codingce@gmail.com
// * @date 2021-01-30 09:11:55
// */
//@Configuration
//public class GulimallSessionConfig {
//
//    @Bean
//    public CookieSerializer cookieSerializer() {
//
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//
//        //放大作用域
//        cookieSerializer.setDomainName("gulimall.com");
//        cookieSerializer.setCookieName("GULISESSION");
//
//        return cookieSerializer;
//    }
//
//
//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }
//
//}
