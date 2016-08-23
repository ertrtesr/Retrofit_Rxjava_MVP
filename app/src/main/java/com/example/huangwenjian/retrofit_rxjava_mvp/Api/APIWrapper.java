package com.example.huangwenjian.retrofit_rxjava_mvp.Api;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class APIWrapper {
    public static void doApi(Observable observable, Subscriber subscriber) {
        observable
                .subscribeOn(Schedulers.io())                   //这连续几个方法都是RxJava里面的
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())      //AndroidSchedulers是RxAndroid里面的类;
                .subscribe(subscriber);
    }
}
