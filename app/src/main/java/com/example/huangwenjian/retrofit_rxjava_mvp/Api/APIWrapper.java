package com.example.huangwenjian.retrofit_rxjava_mvp.api;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
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
    private static Subscription msub;

    public static void doApi(Observable observable, Subscriber subscriber) {
        msub = observable
                .subscribeOn(Schedulers.io())                   //这连续几个方法都是RxJava里面的
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())      //AndroidSchedulers是RxAndroid里面的类;
                .subscribe(subscriber);
    }

    public static void cancel() {
        if (!msub.isUnsubscribed()) {
            msub.unsubscribe();
        }
        msub = null;
    }
}
