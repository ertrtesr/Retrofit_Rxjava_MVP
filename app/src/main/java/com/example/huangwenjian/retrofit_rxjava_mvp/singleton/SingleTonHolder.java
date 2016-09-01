package com.example.huangwenjian.retrofit_rxjava_mvp.singleton;

import com.example.huangwenjian.retrofit_rxjava_mvp.manager.DBManager;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 用于定义一些单例的常量
 * <p/>
 * 时间: 16/8/31
 */
public class SingleTonHolder {
    public static DBManager DEFAULT_INSTANCE = new DBManager(DBManager.DEFAULT_DB_NAME);
    public static DBManager MY_INSTANCE;
}
