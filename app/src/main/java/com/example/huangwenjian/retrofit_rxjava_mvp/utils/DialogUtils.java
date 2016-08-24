package com.example.huangwenjian.retrofit_rxjava_mvp.utils;

import android.app.Dialog;
import android.content.Context;

//import com.example.huangwenjian.retrofit_rxjava_mvp.dialog.CustomDialog;
import com.example.huangwenjian.retrofit_rxjava_mvp.dialog.LoadingDialog;


/**
 * Created by huangwenjian on 2016/8/17.
 */
public class DialogUtils {

    public static Dialog mDialog;

    public static void showDialog(Context context, int layoutId) {
//        mDialog = new CustomDialog(context, layoutId);
//        mDialog.show();
    }

    public static void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * 展示loading对话框
     *
     * @param context
     */
    public static void showLoadingDialog(Context context) {
        mDialog = new LoadingDialog(context);
        mDialog.show();
    }
}
