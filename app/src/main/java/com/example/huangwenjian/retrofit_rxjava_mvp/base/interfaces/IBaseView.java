package com.example.huangwenjian.retrofit_rxjava_mvp.base.interfaces;

/**
 * 作者: huangwenjian
 * -
 * 描述: view层接口基类,提供刷新界面,展示错误界面,展示空界面的三个抽象方法
 * -
 * 日期: 16/8/23
 */
public interface IBaseView<T> {
    void refreshUI(T t);

    void showError();

    void showEmpty();
}
