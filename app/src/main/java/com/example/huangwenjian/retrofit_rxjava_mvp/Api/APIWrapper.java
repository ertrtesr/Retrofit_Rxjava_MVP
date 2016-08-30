package com.example.huangwenjian.retrofit_rxjava_mvp.api;

import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.DownloadSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.RetrofitManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者: huangwenjian
 * -
 * 描述: 访问网络的API包装类
 * -
 * 日期: 16/8/23
 */
public class APIWrapper {
    public static APIService mAPIService;
    private static Subscription mSubscription;

    static {
        //初始化service,只要baseUrl不变则只需要初始化一次就行
        mAPIService = RetrofitManager.getInstance(UIUtils.getActivity()).createService(APIService.class);
    }

    /**
     * 访问网络并进行数据转换的操作
     *
     * @param observable service访问接口得到的observable对象
     * @param subscriber 订阅者
     */
    public static void doApi(Observable observable, BaseSubscriber subscriber) {
        mSubscription = observable
                .subscribeOn(Schedulers.io())                   //这连续几个方法都是RxJava里面的
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())      //AndroidSchedulers是RxAndroid里面的类;
                .subscribe(subscriber);
    }

    public static void doDownload(Observable observable, DownloadSubscriber subscriber) {
        mSubscription = observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 该方法用于取消订阅
     */
    public static void cancel() {
        if (mSubscription != null) {
            if (!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();
            }
            mSubscription = null;
        }
    }
}
