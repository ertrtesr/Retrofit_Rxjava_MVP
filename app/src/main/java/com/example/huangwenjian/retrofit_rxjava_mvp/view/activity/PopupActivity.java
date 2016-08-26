package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.PopupWindowHelper;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 日期: on 16/8/25
 */
public class PopupActivity extends Activity {
    @BindView(R.id.button1)
    Button mButton1;

    @BindView(R.id.button2)
    Button mButton2;

    @BindView(R.id.button3)
    Button mButton3;

    @BindView(R.id.button4)
    Button mButton4;

    @BindView(R.id.button5)
    Button mButton5;

    private View mPopView;
    private PopupWindowHelper mHelper;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = UIUtils.inflate(R.layout.activity_popup);
        setContentView(mRootView);
        ButterKnife.bind(this);
        mPopView = UIUtils.inflate(R.layout.popview_test);
        mHelper = new PopupWindowHelper(mPopView);
    }

    @OnClick(R.id.button1)
    void showAbove() {
        mHelper.init(200, 150).showAbove(mButton1);     //init()方法必须先执行
    }

    @OnClick(R.id.button2)
    void showBelow() {
        mHelper.init(200, ViewGroup.LayoutParams.WRAP_CONTENT).showBelow(mButton2, 20, 0);
    }

    @OnClick(R.id.button3)
    void showFromTop() {
        mHelper.init(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).showFromTop(mRootView);
    }

    @OnClick(R.id.button4)
    void showFromBottom() {
        mHelper.init(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).showFromBottom(mRootView);
    }

    @OnClick(R.id.button5)
    void showInCenter() {
        mHelper.init(UIUtils.dip2Px(200), UIUtils.dip2Px(200)).showInCenter(mRootView);
    }
}
