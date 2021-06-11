package com.example.miaosha.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSelectPreHeat {

    @Resource
    private StockDao stockDao;

    @Test
    public void testPreHeat() {
        System.out.println(stockDao.selectMaxSoldHotStockHasRemain(100));
    }

    @Test
    public void testInitPreHeat() {
        System.out.println(stockDao.selectMaxRemainStock(100));
    }

    @Test
    public void TestCheckHistory() {
        System.out.println(stockDao.hasHistory());
    }
}
