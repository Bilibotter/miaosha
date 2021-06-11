package com.example.miaosha.controller;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.service.StockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Stock)表控制层
 *
 * @author makejava
 * @since 2021-06-09 11:43:06
 */
@RestController
@RequestMapping("stock")
public class StockController {
    /**
     * 服务对象
     */
    @Resource
    private StockService stockService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // @Limit(limitNum = 1)
    @GetMapping("/{id}")
    public Stock selectOne(@PathVariable("id") Integer id) {
        return this.stockService.queryById(id);
    }

}