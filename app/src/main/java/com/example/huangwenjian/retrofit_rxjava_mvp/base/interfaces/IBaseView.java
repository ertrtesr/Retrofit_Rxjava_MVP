package com.example.huangwenjian.retrofit_rxjava_mvp.base.interfaces;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public interface IBaseView<T> {
    void refreshUI(T t);

    void showError();

    void showEmpty();
}
