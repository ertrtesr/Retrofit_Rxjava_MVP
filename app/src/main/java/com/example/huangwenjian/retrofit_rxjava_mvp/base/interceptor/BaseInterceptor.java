package com.example.huangwenjian.retrofit_rxjava_mvp.base.interceptor;

import com.example.huangwenjian.retrofit_rxjava_mvp.event.UrlEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: huangwenjian
 * -
 * 描述: 基础拦截器,在RetrofitManager中添加,用于设置公用的请求头
 * -
 * 日期: 16/8/22
 */
public class BaseInterceptor implements Interceptor {
    private Map<String, String> headers;

    public BaseInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        EventBus.getDefault().post(new UrlEvent(request.url().toString()));     //向Subscriber类中传递url地址
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }
}