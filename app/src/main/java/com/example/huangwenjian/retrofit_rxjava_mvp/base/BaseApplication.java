package com.example.huangwenjian.retrofit_rxjava_mvp.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.huangwenjian.retrofit_rxjava_mvp.gen.UserDao;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.DBManager;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainTreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;
    public static UserDao mUserDao;

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainTreadId() {
        return mMainTreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    @Override
    public void onCreate() {// 程序的入口
        // 初始化化一些.常用属性.然后放到盒子里面来
        // 上下文
        mContext = getApplicationContext();

        // 主线程
        mMainThread = Thread.currentThread();

        // 主线程Id
        mMainTreadId = android.os.Process.myTid();

        // tid thread
        // uid user
        // pid process
        // 主线程Looper对象
        mMainLooper = getMainLooper();

        // 定义一个handler
        mHandler = new Handler();

        initDatabaseOperator();
        super.onCreate();
    }

    /**
     * 初始化数据库表操作对象
     */
    private void initDatabaseOperator() {
        mUserDao = DBManager.getInstance(null).getDaoSession().getUserDao();
    }
}
