package com.example.huangwenjian.retrofit_rxjava_mvp.base;

import rx.Subscriber;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class BaseSubscriber<T> extends Subscriber<T> {
    private BaseNetCallback mCallback;

    public BaseSubscriber(BaseNetCallback callback) {
        mCallback = callback;
    }

    @Override
    public void onCompleted() {
        mCallback.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        mCallback.onError(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        mCallback.onResponse(t);        //跳转到WeatherPresenterImpl类中的onResponse()方法
    }

    @Override
    public void onStart() {
        super.onStart();
        mCallback.onStart();
    }
}
