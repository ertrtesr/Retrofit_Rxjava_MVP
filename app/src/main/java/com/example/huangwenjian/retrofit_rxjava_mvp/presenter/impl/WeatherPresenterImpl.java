package com.example.huangwenjian.retrofit_rxjava_mvp.presenter.impl;

import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseNetCallback;
import com.example.huangwenjian.retrofit_rxjava_mvp.bean.WeatherBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.impl.WeatherModelImpl;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.interfaces.IWeatherModel;
import com.example.huangwenjian.retrofit_rxjava_mvp.presenter.interfaces.IWeatherPresenter;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.interfaces.IWeatherView;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -~
 * 日期: 16/8/22
 */
public class WeatherPresenterImpl extends BaseNetCallback<WeatherBean> implements IWeatherPresenter {
    //presenter实现类中同时持有model和view层的引用
    private IWeatherModel mModel;
    private IWeatherView mView;

    public WeatherPresenterImpl(IWeatherView view) {
        mView = view;
        mModel = new WeatherModelImpl(this);
    }

    @Override
    public void getWeather() {
        mModel.getWeather();
    }


    @Override
    public void onResponse(WeatherBean weatherBean) {
        mView.refreshUI(weatherBean);
    }

    @Override
    public void onError(String e) {

    }
}
