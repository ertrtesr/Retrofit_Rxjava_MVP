package com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/8/31
 */
@Entity
public class Person {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String sex;
    private String address;
    @Generated(hash = 1127805686)
    public Person(long id, String name, String sex, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
