package com.example.miaosha.service;

import com.example.miaosha.entity.StockOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (StockOrder)表服务接口
 *
 * @author makejava
 * @since 2021-06-09 11:58:06
 */
@Service
public interface StockOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StockOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    StockOrder insert(StockOrder stockOrder);

    /**
     * 修改数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    int update(StockOrder stockOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int delOrderDBBefore();

    int createNewOrderWithOptimisticLock(int sid) throws Exception;

    int CreateNewOrderWithOptimisticLockAndRedis(int sid)throws  Exception;

    int CreateNewOrderWithOptimisticLockAndRedisLimit(int sid) throws Exception;

}