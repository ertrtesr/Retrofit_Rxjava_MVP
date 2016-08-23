package com.example.huangwenjian.retrofit_rxjava_mvp.presenter.impl;

import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseNetCallback;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.UserBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.impl.UserModelImpl;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.interfaces.IUserModel;
import com.example.huangwenjian.retrofit_rxjava_mvp.presenter.interfaces.IUserPresenter;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.interfaces.IUserView;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class UserPresenterImpl extends BaseNetCallback<UserBean> implements IUserPresenter {
    private IUserModel mModel;
    private IUserView mView;

    public UserPresenterImpl(IUserView view) {
        mView = view;
        mModel = new UserModelImpl(this);
    }

    @Override
    public void onResponse(UserBean userBean) {
        mView.refreshUI(userBean);
    }

    @Override
    public void onError(String e) {
        mView.showError();
    }

    @Override
    public void getUser() {
        mModel.getUser();
    }
}
