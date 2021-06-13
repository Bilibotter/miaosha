package com.example.miaosha.limit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: YHM
 * @Date: 2021/6/10 10:45
 */
@Component
public class LimitStream {
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private DefaultRedisScript<Long> script;
    private static Integer limitValue = 5;

    public boolean limit() {
        List<String> keys = Collections.singletonList(String.valueOf(System.currentTimeMillis() / 1000));
        return template.execute(script, keys, limitValue) > 0;
    }
}
