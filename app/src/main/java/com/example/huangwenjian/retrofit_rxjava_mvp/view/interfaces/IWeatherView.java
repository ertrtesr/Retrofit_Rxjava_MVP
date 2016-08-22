package com.example.huangwenjian.retrofit_rxjava_mvp.view.interfaces;

/**
 * 作者: huangwenjian
 * -
 * 描述: View层接口
 * -
 * 日期: 16/8/22
 */
public interface IWeatherView<T> {
    void refreshUI(T t);
}
