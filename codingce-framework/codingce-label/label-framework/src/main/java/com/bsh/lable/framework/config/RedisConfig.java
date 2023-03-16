//package com.bsh.lable.framework.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.integration.redis.util.RedisLockRegistry;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author mxz
// */
//@Configuration
//public class RedisConfig {
//
//    /**
//     * lock
//     *
//     * @param redisConnectionFactory redisConnectionFactory
//     * @return redisLockRegistry
//     */
//    @Bean
//    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisLockRegistry(redisConnectionFactory, "redis-lock",
//                TimeUnit.MINUTES.toMillis(10));
//    }
//
//    /**
//     * redis(反)序列化
//     *
//     * @param redisConnectionFactory redisConnectionFactory
//     * @return redisTemplate
//     */
//    @Bean
//    RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper());
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        return redisTemplate;
//    }
//}
