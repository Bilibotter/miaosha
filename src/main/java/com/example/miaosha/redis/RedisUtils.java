package com.example.miaosha.redis;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.redis.StockPrefix;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @Author: YHM
 * @Date: 2021/6/10 16:39
 */
// Redis的淘汰策略一定要为noeviction
// 因为完全在内存中操作数据
public class RedisUtils {
    @Resource
    private static StringRedisTemplate template;

    // 也许可以在无存货时更新数据库或者每20个版本修改一次数据库
    public static void updateCache(int id) {
        template.opsForValue().increment(StockPrefix.VERSION+id);
        template.opsForValue().increment(StockPrefix.SOLD+id);
        template.opsForValue().decrement(StockPrefix.REMAIN+id);
    }
}
