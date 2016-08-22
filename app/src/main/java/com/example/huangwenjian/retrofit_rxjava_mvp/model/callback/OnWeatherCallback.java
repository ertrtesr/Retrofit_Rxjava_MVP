package com.example.huangwenjian.retrofit_rxjava_mvp.model.callback;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public interface OnWeatherCallback<T> {
    void onSuccess(T t);

    void onError(String e);
}
