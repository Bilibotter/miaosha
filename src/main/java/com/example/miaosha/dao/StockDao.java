package com.example.miaosha.dao;

import com.example.miaosha.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Stock)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-09 11:43:06
 */
@Repository
public interface StockDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Stock queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Stock> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stock 实例对象
     * @return 对象列表
     */
    List<Stock> queryAll(Stock stock);

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int insert(Stock stock);

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int update(Stock stock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 使用乐观锁修改数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int addStackWithOptimisticLock(Stock stock);

    /**
     * 在有历史记录的情况下返回预热数据
     *
     * @param limit 热点数据的数据量
     * @return 仍有库存的热点数据
     */
    List<Stock> selectMaxSoldHotStockHasRemain(int limit);

    /**
     * 将库存最多的商品作为热点数据返回
     * 适用于无历史记录的情况
     *
     * @param limit 热点数据的数据量
     * @return 库存最多的数据
     */
    List<Stock> selectMaxRemainStock(int limit);

    /**
     * 查询是否有一条版本号不为0的记录
     */
    Integer hasHistory();

}