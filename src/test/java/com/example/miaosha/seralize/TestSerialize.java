package com.example.miaosha.seralize;

import com.example.miaosha.controller.StockController;
import com.example.miaosha.dao.StockDao;
import com.example.miaosha.entity.Stock;
import com.example.miaosha.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSerialize {
    @Resource
    private StockDao dao;

    @ResponseBody
    private Stock getStock() {
        return dao.queryById(1);
    }

    @Test
    public void TestResult() {
        System.out.println(getStock().getClass());
    }
}
