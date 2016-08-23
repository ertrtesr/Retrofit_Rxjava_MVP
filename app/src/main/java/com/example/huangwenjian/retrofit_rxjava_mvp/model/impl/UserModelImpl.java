package com.example.huangwenjian.retrofit_rxjava_mvp.model.impl;

import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIService;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIWrapper;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseNetCallback;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.RetrofitManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.UserBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.interfaces.IUserModel;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class UserModelImpl implements IUserModel {
    private BaseNetCallback<UserBean> mCallback;

    public UserModelImpl(BaseNetCallback<UserBean> callback) {
        mCallback = callback;
    }

    @Override
    public void getUser(String name) {
        APIService service = RetrofitManager.getInstance(UIUtils.getActivity(), "http://myclients.duapp.com/").createService(APIService.class);
        APIWrapper.doApi(service.getUserInfo(name), new BaseSubscriber(mCallback));
    }
}
