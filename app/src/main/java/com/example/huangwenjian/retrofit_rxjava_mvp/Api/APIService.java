package com.example.huangwenjian.retrofit_rxjava_mvp.api;

import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.WeatherBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public interface APIService {

    public static final String BASE_URL = "http://www.weather.com.cn/";

    @GET("data/cityinfo/101010100.html")
    Observable<WeatherBean> getWeatherInfo();
}
