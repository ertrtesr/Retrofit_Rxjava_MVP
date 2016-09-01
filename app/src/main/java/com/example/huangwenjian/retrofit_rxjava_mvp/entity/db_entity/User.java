package com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/8/30
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private int age;
    private int girl;
    private int boy;
    private String phone;

    @Generated(hash = 1611543751)
    public User(long id, String name, int age, int girl, int boy, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.girl = girl;
        this.boy = boy;
        this.phone = phone;
    }

    @Generated(hash = 586692638)
    public User() {
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGirl() {
        return this.girl;
    }

    public void setGirl(int girl) {
        this.girl = girl;
    }

    public int getBoy() {
        return this.boy;
    }

    public void setBoy(int boy) {
        this.boy = boy;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
