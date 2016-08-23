package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.UserBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.presenter.impl.UserPresenterImpl;
import com.example.huangwenjian.retrofit_rxjava_mvp.presenter.interfaces.IUserPresenter;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.interfaces.IUserView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class UserActivity extends Activity implements IUserView<UserBean> {
    @BindView(R.id.btn_get_user)
    Button mBtn_get_user;

    @BindView(R.id.tv_show_user)
    TextView mTv_show_user;

    private IUserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        mUserPresenter = new UserPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UIUtils.mActivity = this;
    }

    @OnClick(R.id.btn_get_user)
    public void click() {
        //请求网络
        mUserPresenter.getUser();
    }

    @Override
    public void refreshUI(UserBean userBean) {
        mTv_show_user.setText(userBean.soga.name);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }
}
