package com.example.huangwenjian.retrofit_rxjava_mvp.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: huangwenjian
 * -
 * 描述:Retrofit管理类
 * -
 * 日期: 16/8/22
 */
public class RetrofitManager {
    private static RetrofitManager mInstance;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private RetrofitManager() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .build();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.weather.com.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
    }

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null)
                    mInstance = new RetrofitManager();
            }
        }
        return mInstance;
    }

    /**
     * 创建APIService
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
