package com.example.huangwenjian.retrofit_rxjava_mvp.model.impl;

import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIWrapper;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseNetCallback;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.WeatherBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.interfaces.IWeatherModel;

/**
 * 作者: huangwenjian
 * -
 * 描述: model层实现类
 * -
 * 日期: 16/8/22
 */
public class WeatherModelImpl implements IWeatherModel {
    private BaseNetCallback<WeatherBean> mCallback;

    public WeatherModelImpl(BaseNetCallback callback) {
        mCallback = callback;       //callback就是WeatherPresenterImpl的实例
    }

    /**
     * 获取天气信息的方法
     */
    @Override
    public void getWeather() {
        //        APIService service = RetrofitManager.getInstance(UIUtils.getActivity()).createService(APIService.class);
        APIWrapper.doApi(APIWrapper.mAPIService.getWeatherInfo(), new BaseSubscriber<WeatherBean>(mCallback));     //getWeatherInfo()是APIService中的方法
    }
}