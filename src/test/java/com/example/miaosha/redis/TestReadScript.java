package com.example.miaosha.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @Author: YHM
 * @Date: 2021/6/10 10:49
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestReadScript {
    @Test
    public void testReadScript() throws IOException {
        String script = ScriptReader.getScript("limit.lua");
        System.out.println(script);
    }
}
