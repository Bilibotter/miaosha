package com.example.miaosha.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPreHear {
    @Autowired
    RedisPreHeat redisPreHeat;

    @Test
    public void TestPreHeat() throws Exception {
        redisPreHeat.run(new DefaultApplicationArguments());
    }
}
