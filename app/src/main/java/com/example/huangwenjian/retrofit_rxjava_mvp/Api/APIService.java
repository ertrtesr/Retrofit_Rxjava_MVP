package com.example.huangwenjian.retrofit_rxjava_mvp.api;

import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.UserBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.WeatherBean;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
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
//    public static final String BASE_URL = "http://myclients.duapp.com/";


    @GET("data/cityinfo/101010100.html")
    Observable<WeatherBean> getWeatherInfo();

    @GET("n8/number/dictionary")
    Observable<UserBean> getUserInfo(@Query("imsi") String name);

    @Streaming
    @GET("11.jpg")
    Observable<ResponseBody> downloadFile();
}
