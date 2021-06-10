package com.example.miaosha.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @Author: YHM
 * @Date: 2021/6/10 11:16
 */
@Configuration
public class LuaConfiguration {
    @Bean
    public DefaultRedisScript<Number> redisScript() {
        DefaultRedisScript<Number> redisScript
    }
}
