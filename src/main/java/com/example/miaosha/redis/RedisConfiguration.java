package com.example.miaosha.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: YHM
 * @Date: 2021/6/10 16:56
 */
@Configuration
public class RedisConfiguration {
    @Bean
    RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key-value结构序列化数据结构
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash数据结构序列化方式,必须这样否则存hash 就是基于jdk序列化的
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 启用默认序列化方式
        template.setEnableDefaultSerializer(true);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}
