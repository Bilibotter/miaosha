package com.example.miaosha.dao;

import com.example.miaosha.entity.StockOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (StockOrder)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-09 11:58:06
 */
@Repository
public interface StockOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockOrder queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StockOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stockOrder 实例对象
     * @return 对象列表
     */
    List<StockOrder> queryAll(StockOrder stockOrder);

    /**
     * 新增数据
     *
     * @param stockOrder 实例对象
     * @return 影响行数
     */
    int insert(StockOrder stockOrder);

    /**
     * 修改数据
     *
     * @param stockOrder 实例对象
     * @return 影响行数
     */
    int update(StockOrder stockOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键删除数据
     *
     * @return 影响行数
     */
    int delOrderDBBefore();

}