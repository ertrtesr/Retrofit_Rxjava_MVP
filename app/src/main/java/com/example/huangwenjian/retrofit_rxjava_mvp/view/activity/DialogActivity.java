package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.LoadingDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/24
 */
public class DialogActivity extends Activity {
    @BindView(R.id.btn_show_loading)
    Button mBtn_show_loading;

    @BindView(R.id.btn_hide_loading)
    Button mBtn_hide_loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_show_loading, R.id.btn_hide_loading})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_show_loading:
                LoadingDialogUtils.show(this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingDialogUtils.hide();
                    }
                }, 2000);
                break;
            case R.id.btn_hide_loading:
                LoadingDialogUtils.hide();
                break;
            default:
                break;
        }
    }
}
