package com.example.miaosha.redis;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.service.StockService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisPreHeat implements ApplicationRunner {
    @Resource
    private StockService service;

    @Resource
    private StringRedisTemplate template;

    @Value("${spring.redis.preheat-limit}")
    private int limit;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Stock> stocks;
        stocks = service.hasHistory() ? service.getHotStocks(limit) : service.getInitHotStocks(limit);
        for (Stock stock:stocks) {
            template.opsForValue().set(StockPrefix.REMAIN+stock.getId(), stock.getRemain().toString());
            template.opsForValue().set(StockPrefix.SOLD+stock.getId(), stock.getSold().toString());
            template.opsForValue().set(StockPrefix.VERSION+stock.getId(), stock.getVersion().toString());
        }
    }
}
