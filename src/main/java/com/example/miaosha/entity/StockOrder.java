package com.example.miaosha.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (StockOrder)实体类
 *
 * @author makejava
 * @since 2021-06-09 11:58:06
 */
public class StockOrder implements Serializable {
    private static final long serialVersionUID = 399441524223633857L;
    
    private Integer id;
    
    private Integer sid;
    
    private String name;
    
    private Date creatTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

}