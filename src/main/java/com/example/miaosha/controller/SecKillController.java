package com.example.miaosha.controller;

import com.example.miaosha.service.StockOrderService;
import com.example.miaosha.service.StockService;
import com.example.miaosha.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SecKillController {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockOrderService orderService;

    @PostMapping("")
    public String submitOrder(HttpServletRequest request, int stockId) {
        int result = orderService.createNewOrderWithOptimisticLock()
    }
}
