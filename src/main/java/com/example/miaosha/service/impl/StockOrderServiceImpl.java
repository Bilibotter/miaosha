package com.example.miaosha.service.impl;

import com.example.miaosha.entity.StockOrder;
import com.example.miaosha.dao.StockOrderDao;
import com.example.miaosha.service.StockOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StockOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-06-09 11:58:06
 */
@Service("stockOrderService")
public class StockOrderServiceImpl implements StockOrderService {
    @Resource
    private StockOrderDao stockOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StockOrder queryById(Integer id) {
        return this.stockOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<StockOrder> queryAllByLimit(int offset, int limit) {
        return this.stockOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    @Override
    public StockOrder insert(StockOrder stockOrder) {
        this.stockOrderDao.insert(stockOrder);
        return stockOrder;
    }

    /**
     * 修改数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    @Override
    public StockOrder update(StockOrder stockOrder) {
        this.stockOrderDao.update(stockOrder);
        return this.queryById(stockOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stockOrderDao.deleteById(id) > 0;
    }
}