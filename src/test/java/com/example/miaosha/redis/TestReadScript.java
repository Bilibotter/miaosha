package com.example.miaosha.redis;

import com.example.miaosha.limit.ScriptReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @Author: YHM
 * @Date: 2021/6/10 10:49
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestReadScript {

    @Resource
    private DefaultRedisScript<Long> limit;

    @Resource
    private StringRedisTemplate template;

    @Test
    public void testReadScript() throws IOException {
        String script = ScriptReader.getScript("limit.lua");
        System.out.println(script);
    }

    @Test
    public void testExecuteScript() {
        List<String> keys = Collections.singletonList(String.valueOf(System.currentTimeMillis() / 1000));
        Long result = template.execute(limit, keys, "10");
        System.out.println("Result: "+result);
    }

}
