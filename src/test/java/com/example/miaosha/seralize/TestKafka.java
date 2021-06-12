package com.example.miaosha.seralize;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.service.impl.StockOrderServiceImpl;
import com.example.miaosha.service.impl.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestKafka {
    @Resource
    StockOrderServiceImpl service;

    @Resource
    StockServiceImpl stockService;

    @Resource
    KafkaTemplate template;

    @Autowired
    FSTConfiguration configuration;

    @Test
    public void testKafkaConsume() throws Exception {
        Stock stock = stockService.queryById(1);
        System.out.println("Stock"+stockService.queryById(1));
        template.send("miaosha", stock);
        System.out.println(service.consumerFromKafka(stock));
        System.out.println("Stock"+stockService.queryById(1));
    }
}
