package com.example.huangwenjian.retrofit_rxjava_mvp.model.impl;

import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIService;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIWrapper;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseNetCallback;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.RetrofitManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.WeatherBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.interfaces.IWeatherModel;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public class WeatherModelImpl implements IWeatherModel {
    private BaseNetCallback<WeatherBean> mCallback;

    public WeatherModelImpl(BaseNetCallback callback) {
        mCallback = callback;
    }

    @Override
    public void getWeather() {
        APIService service = RetrofitManager.getInstance(UIUtils.getContext()).createService(APIService.class);
        APIWrapper.doApi(service.getWeatherInfo(), new BaseSubscriber<WeatherBean>(mCallback));
    }
}
