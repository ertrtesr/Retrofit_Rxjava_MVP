package com.example.huangwenjian.retrofit_rxjava_mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;

import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseApplication;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public class UIUtils {
    public static Activity mActivity;
    private static LayoutInflater mInflater;

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return BaseApplication.getContext();
    }

    /**
     * 得到activity
     *
     * @return
     */
    public static Activity getActivity() {
        return mActivity;
    }

    /**
     * 得到Resouce对象
     */
    public static Resources getResource() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串
     */
    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    /**
     * 得到String.xml中的字符串,带占位符
     */
    public static String getString(int id, Object... formatArgs) {
        return getResource().getString(id, formatArgs);
    }

    /**
     * 得到String.xml中的字符串数组
     */
    public static String[] getStringArr(int resId) {
        return getResource().getStringArray(resId);
    }

    /**
     * 得到colors.xml中的颜色
     */
    public static int getColor(int colorId) {
        return getResource().getColor(colorId);
    }

    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到主线程id
     */
    public static long getMainThreadid() {
        return BaseApplication.getMainTreadId();
    }

    /**
     * 得到主线程Handler
     */
    public static Handler getMainThreadHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * 安全的执行一个任务
     */
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();

        if (curThreadId == getMainThreadid()) {// 如果当前线程是主线程
            task.run();
        } else {// 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }

    /**
     * 延迟执行任务
     */
    public static void postTaskDelay(Runnable task, int delayMillis) {
        getMainThreadHandler().postDelayed(task, delayMillis);
    }

    /**
     * 移除任务
     */
    public static void removeTask(Runnable task) {
        getMainThreadHandler().removeCallbacks(task);
    }

    /**
     * dip-->px
     */
    public static int dip2Px(int dip) {
        // px/dip = density;
        float density = getResource().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2Dip(int px) {
        // px/dip = density;
        float density = getResource().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }

    public static View inflate(int resId) {
        mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(resId, null);
        return view;
    }

    public static boolean isMainThread() {
        return Process.myTid() == getMainThreadid();
    }
}
