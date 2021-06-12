package com.example.miaosha.redis;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.redis.StockPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: YHM
 * @Date: 2021/6/10 16:39
 */
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate template;

    // 也许可以在无存货时更新数据库或者每20个版本修改一次数据库
    public void updateCache(int id) {
        template.opsForValue().increment(StockPrefix.VERSION+id);
        template.opsForValue().increment(StockPrefix.SOLD+id);
        template.opsForValue().decrement(StockPrefix.REMAIN+id);
    }
}
