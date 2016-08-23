package com.example.huangwenjian.retrofit_rxjava_mvp.base;

/**
 * 作者: huangwenjian
 * -
 * 描述: 网络回调的接口
 * -
 * 日期: 16/8/23
 */
public abstract class BaseNetCallback<T> {
    
    public abstract void onResponse(T t);

    public abstract void onError(String e);

    public void onStart() {

    }

    public void onComplete() {

    }
}

