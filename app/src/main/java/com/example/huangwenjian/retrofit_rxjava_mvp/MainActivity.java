package com.example.huangwenjian.retrofit_rxjava_mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.view.activity.DatabaseActivity;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.activity.DownloadActivity;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.activity.ExpandbleTextActivity;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.activity.PopupActivity;
import com.example.huangwenjian.retrofit_rxjava_mvp.view.activity.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindView(R.id.btn_enter_weather)
    Button mBtn_enter_weather;

    @BindView(R.id.btn_enter_pop)
    Button mBtn_enter_pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_enter_weather)
    public void start() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_enter_pop)
    void enterPop() {
        Intent intent = new Intent(this, PopupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_enter_expandtext)
    void enterExpandText() {
        Intent intent = new Intent(this, ExpandbleTextActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_enter_download)
    void enterDownload() {
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_enter_database)
    void enterDatabase(){
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }
}
