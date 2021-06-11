package com.example.miaosha.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @Author: YHM
 * @Date: 2021/6/10 11:16
 */
@Configuration
public class LuaConfiguration {
    @Bean
    public DefaultRedisScript<Long> redisScript() {
        DefaultRedisScript<Long> limitScript = new DefaultRedisScript<>();
        limitScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
        limitScript.setResultType(Long.class);
        return limitScript;
    }
}
