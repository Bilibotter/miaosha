package com.example.miaosha.service.impl;

import com.example.miaosha.entity.Stock;
import com.example.miaosha.entity.StockOrder;
import com.example.miaosha.dao.StockOrderDao;
import com.example.miaosha.redis.StockPrefix;
import com.example.miaosha.redis.RedisUtils;
import com.example.miaosha.service.StockOrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private StockServiceImpl stockServiceImpl;

    @Resource
    private StringRedisTemplate template;

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
    public int update(StockOrder stockOrder) {
        return this.stockOrderDao.update(stockOrder);
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

    @Override
    public int delOrderDBBefore() {
        return stockOrderDao.delOrderDBBefore();
    }

    // 等待Kafka
    @Override
    public int createNewOrderWithOptimisticLock(int sid) throws Exception {
        Stock stock = checkRemain(sid);
        return 0;
    }

    @Override
    public int CreateNewOrderWithOptimisticLockAndRedis(int sid) throws Exception {
        Stock stock = checkRemainFromRedis(sid);
        return 0;
    }

    public Stock checkRemain(int sid) throws RuntimeException {
        Stock stock = stockServiceImpl.queryById(sid);
        if (stock.getRemain() < 1) {
            throw new RuntimeException("Remain is 0.");
        }
        return stock;
    }

    // 准备加入redis不存在时从数据库获取
    public Stock checkRemainFromRedis(int sid) throws RuntimeException {
        Integer remain = Integer.valueOf(template.opsForValue().get(StockPrefix.REMAIN+sid));
        if (remain < 1) {
            throw new RuntimeException("Remain is 0.");
        }
        Stock stock = new Stock();
        stock.setRemain(remain);
        stock.setSold(Integer.valueOf(template.opsForValue().get(StockPrefix.SOLD+sid)));
        stock.setVersion(Integer.valueOf(template.opsForValue().get(StockPrefix.VERSION+sid)));
        stock.setName("DefaultName");
        return stock;
    }

    // 原版不修改版本
    public int saleStock(Stock stock) {
        stock.setRemain(stock.getRemain()-1);
        stock.setSold(stock.getSold()+1);
        stock.setVersion(stock.getVersion()+1);
        return stockServiceImpl.update(stock);
    }

    public int saleStockWithOptimisticLock(Stock stock) throws RuntimeException {
        int result = stockServiceImpl.addStackWithOptimisticLock(stock);
        if(result == 0){
            throw  new RuntimeException("Invalid stock info, because stock has changed during order!");
        }
        return result;
    }

    public int saleStockWithOptimisticLockRedis(Stock stock) throws RuntimeException {
        int result = stockServiceImpl.addStackWithOptimisticLock(stock);
        if (result == 0) {
            throw new RuntimeException("Invalid stock info, because stock has changed during order!");
        }
        RedisUtils.updateCache(stock.getId());
        return result;
    }

    // 准备加入Kafka
    @Override
    public int CreateNewOrderWithOptimisticLockAndRedisLimit(int sid) throws Exception {
        Stock stock = checkRemainFromRedis(sid);
        saleStockWithOptimisticLockRedis(stock);
        return creatOrder(stock);
    }
    
    public int createWrongOrder(int sid) {
        Stock stock = checkRemain(sid);
        saleStock(stock);
        return creatOrder(stock);
    }

    private int creatOrder(Stock stock) throws RuntimeException {
        StockOrder order = new StockOrder();
        order.setCreatTime(new Date());
        order.setSid(stock.getId());
        order.setName(stock.getName());
        int result = stockServiceImpl.insert(stock);
        if (result == 0) {
            throw new RuntimeException("Fail to create order");
        }
        return result;
    }
}