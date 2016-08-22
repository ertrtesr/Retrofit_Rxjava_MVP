package com.example.huangwenjian.retrofit_rxjava_mvp.Api;

import com.example.huangwenjian.retrofit_rxjava_mvp.bean.WeatherBean;

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
    @GET("data/cityinfo/101010100.html")
    Observable<WeatherBean> getWeatherInfo();
}
