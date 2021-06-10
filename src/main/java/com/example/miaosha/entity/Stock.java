package com.example.miaosha.entity;

import java.io.Serializable;

/**
 * (Stock)实体类
 *
 * @author makejava
 * @since 2021-06-09 11:43:06
 */
public class Stock implements Serializable {
    private static final long serialVersionUID = -35078531939695954L;
    
    private Integer id;
    
    private String name;
    
    private Integer remain;
    
    private Integer sold;
    
    private Integer version;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}