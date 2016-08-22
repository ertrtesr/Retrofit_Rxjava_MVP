package com.example.huangwenjian.retrofit_rxjava_mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.view.activity.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindView(R.id.btn_start)
    Button mBtn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start)
    public void start() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }
}
