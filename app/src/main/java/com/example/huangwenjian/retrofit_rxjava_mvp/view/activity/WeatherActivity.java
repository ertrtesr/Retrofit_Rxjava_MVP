package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIWrapper;
import com.example.huangwenjian.retrofit_rxjava_mvp.model.bean.WeatherBean;
import com.example.huangwenjian.retrofit_rxjava_mvp.presenter.impl.WeatherPresenterImpl;
import com.example.huangwenjian.retrofit_rxjava_mvp.presenter.interfaces.IWeatherPresenter;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.interfaces.IWeatherView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public class WeatherActivity extends Activity implements IWeatherView<WeatherBean> {
    @BindView(R.id.btn_weather)
    Button mBtn_weather;

    @BindView(R.id.tv_weather)
    TextView mTv_weather;

    private IWeatherPresenter mPresenter;       //持有Presenter层引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        UIUtils.mActivity = this;
        ButterKnife.bind(this);
        mPresenter = new WeatherPresenterImpl(this);
    }

    @OnClick(R.id.btn_weather)
    public void click() {
        mPresenter.getWeather();        //跳转到WeatherPresenterImpl类中的getWeather()方法
    }


    @Override
    public void refreshUI(WeatherBean weatherBean) {
        WeatherBean.WeatherinfoBean weatherinfo = weatherBean.weatherinfo;
        String city = weatherinfo.city;
        String weather = weatherinfo.weather;

        mTv_weather.setText(city + " " + weather);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        APIWrapper.cancel();
    }
}
