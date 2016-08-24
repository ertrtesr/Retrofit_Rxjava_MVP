package com.example.huangwenjian.retrofit_rxjava_mvp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.listener.OnDialogClickListener;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView mTv_cancel;
    private TextView mTv_confirm;
    private OnDialogClickListener mListener;

    public CustomDialog(Context context, int layoutId) {
        super(context, R.style.CustomDialogStyle);
        setContentView(layoutId);
        initView();
        initListener();
    }

    private void initListener() {
        mTv_confirm.setOnClickListener(this);
        mTv_cancel.setOnClickListener(this);
    }

    private void initView() {
        mTv_cancel = (TextView) findViewById(R.id.tv_cancel);
        mTv_confirm = (TextView) findViewById(R.id.tv_confirm);
    }

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (mListener != null) {
                    mListener.doConfirm();
                }
                break;
            case R.id.tv_cancel:
                if (mListener != null) {
                    mListener.doCancel();
                }
                break;
            default:
                break;
        }
    }
}
