package com.example.huangwenjian.retrofit_rxjava_mvp.manager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIService;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.interceptor.BaseInterceptor;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.interceptor.CaheInterceptor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: huangwenjian
 * -
 * 描述: Retrofit管理类
 * -
 * 日期: 16/8/22
 */
public class RetrofitManager {
    private static Context mContext;
    private static RetrofitManager mInstance;
    private Retrofit retrofit;
    private static final int DEFAULT_TIMEOUT = 10000;
    private APIService apiService;
    private static OkHttpClient okHttpClient;
    private static String baseUrl = APIService.BASE_URL;
    private Cache cache = null;
    private File httpCacheDirectory;

    private RetrofitManager() {

    }

    private RetrofitManager(Context context) {
        this(context, baseUrl);
    }

    /**
     * 两个参数的构造方法
     *
     * @param context
     * @param url     自定义的baseUrl
     */
    private RetrofitManager(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(context.getCacheDir(), "retrofit_cache");
        }
        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);        //创建10MB的缓存空间
            }
        } catch (Exception e) {
            Log.e("OkHttp", "Could not create http cache", e);
        }

        Map<String, String> headers = null;
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new CaheInterceptor(context))
                .addNetworkInterceptor(new CaheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(4, 10 * 1000, TimeUnit.MILLISECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里4个，和每个保持时间为10s
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    private static class SingletonHolder {
        private static RetrofitManager INSTANCE = new RetrofitManager(mContext);
    }

    /**
     * 单参,默认的baseUrl
     *
     * @param context
     * @return
     */
    public static RetrofitManager getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param context
     * @param url     当baseUrl需要自定义时,手动传入即可
     * @return
     */
    public static RetrofitManager getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        return new RetrofitManager(context, url);
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
