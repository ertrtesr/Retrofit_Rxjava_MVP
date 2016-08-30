package com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity;

import org.greenrobot.greendao.annotation.Entity;
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
    public int id;
    public String name;
    public int age;
    public boolean isBoy;
}
