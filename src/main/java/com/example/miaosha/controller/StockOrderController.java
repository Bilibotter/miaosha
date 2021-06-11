package com.example.miaosha.controller;

import com.example.miaosha.entity.StockOrder;
import com.example.miaosha.service.StockOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (StockOrder)表控制层
 *
 * @author makejava
 * @since 2021-06-09 11:58:06
 */
@RestController
@RequestMapping("order")
public class StockOrderController {
    /**
     * 服务对象
     */
    @Resource
    private StockOrderService stockOrderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // @Limit(limitNum = 1)
    @GetMapping("/{id}")
    public StockOrder selectOne(@PathVariable("id") Integer id) {
        return this.stockOrderService.queryById(id);
    }

}