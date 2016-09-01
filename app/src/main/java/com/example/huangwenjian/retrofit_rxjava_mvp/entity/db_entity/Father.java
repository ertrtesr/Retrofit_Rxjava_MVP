package com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/9/1
 */
@Entity
public class Father {
    @Id(autoincrement = true)
    private long id;
    private String sex;
    @Generated(hash = 1292056204)
    public Father(long id, String sex) {
        this.id = id;
        this.sex = sex;
    }
    @Generated(hash = 383274692)
    public Father() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
