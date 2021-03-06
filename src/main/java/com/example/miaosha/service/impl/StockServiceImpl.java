package com.example.miaosha.service.impl;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.dao.StockDao;
import com.example.miaosha.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Stock)表服务实现类
 *
 * @author makejava
 * @since 2021-06-09 11:43:06
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Resource
    private StockDao stockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Stock queryById(Integer id) {
        return this.stockDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Stock> queryAllByLimit(int offset, int limit) {
        return this.stockDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Stock stock) {
        return this.stockDao.insert(stock);
    }

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Stock stock) {
        return this.stockDao.update(stock);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stockDao.deleteById(id) > 0;
    }

    @Override
    public int addStackWithOptimisticLock(Stock stock) {
        return stockDao.addStackWithOptimisticLock(stock);
    }

    @Override
    public List<Stock> getHotStocks(int limit) {
        return stockDao.selectMaxSoldHotStockHasRemain(limit);
    }

    @Override
    public List<Stock> getInitHotStocks(int limit) {
        return stockDao.selectMaxRemainStock(limit);
    }

    @Override
    public boolean hasHistory() {
        return stockDao.hasHistory() != null;
    }
}