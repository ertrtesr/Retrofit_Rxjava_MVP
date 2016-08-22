package com.example.huangwenjian.retrofit_rxjava_mvp.model.impl;

import com.example.huangwenjian.retrofit_rxjava_mvp.Api.APIService;
import com.example.huangwenjian.retrofit_rxjava_mvp.bean.WeatherBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.RetrofitManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.callback.OnWeatherCallback;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.interfaces.IWeatherModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public class WeatherModelImpl implements IWeatherModel {
    private OnWeatherCallback<WeatherBean> mCallback;

    public WeatherModelImpl(OnWeatherCallback callback) {
        mCallback = callback;
    }

    @Override
    public void getWeather() {
        APIService service = RetrofitManager.getInstance().createService(APIService.class);
        service.getWeatherInfo()
                .subscribeOn(Schedulers.io())                   //这连续几个方法都是RxJava里面的
                .observeOn(AndroidSchedulers.mainThread())      //AndroidSchedulers是RxAndroid里面的类;
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mCallback != null) {
                            mCallback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        if (mCallback != null) {
                            if (weatherBean != null) {
                                mCallback.onSuccess(weatherBean);
                            } else {
                                mCallback.onError("null");
                            }
                        }
                    }
                });
    }
}
