package com.example.huangwenjian.retrofit_rxjava_mvp.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: PopupWindow工具类
 * <p/>
 * 日期: 16/8/25
 */
public class PopupWindowHelper {
    private View popupView;
    private PopupWindow mPopupWindow;
    private InnerPopupWindowHelper mInnerHelper;
    private int mPopWidth;      //popupwindow的宽度
    private int mPopHeight;     //popupwindow的高度
    private static final int TYPE_WRAP_CONTENT = 0, TYPE_MATCH_PARENT = 1;

    public PopupWindowHelper(View view) {
        popupView = view;
    }

    /**
     * 初始化popupwindow大小,必须执行的方法
     *
     * @param width
     * @param height
     */
    public InnerPopupWindowHelper init(int width, int height) {
        mPopupWindow = new PopupWindow(popupView, width, height);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopWidth = mPopupWindow.getWidth();        //获取popupwindow的宽度
        mPopHeight = mPopupWindow.getHeight();      //获取popupwindow的高度
        setCancelable(true);
        if (mInnerHelper == null) {
            mInnerHelper = new InnerPopupWindowHelper();
        }
        return mInnerHelper;
    }

    /**
     * 点击popupwindow以外的地方消失, 默认为true
     *
     * @param isCancelable
     */
    public void setCancelable(boolean isCancelable) {
        if (mPopupWindow == null) {
            return;
        }
        if (isCancelable) {
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
        } else {
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.setFocusable(false);
        }
    }

    /**
     * 内部类
     */
    public class InnerPopupWindowHelper {

        public void showBelow(View anchor) {
            showBelow(anchor, 0, 0);
        }

        public void showBelow(View anchor, int xoff, int yoff) {
            if (mPopupWindow != null)
                mPopupWindow.showAsDropDown(anchor, xoff, yoff);
        }

        public void showAtLocation(View parent, int gravity, int x, int y) {
            if (mPopupWindow != null)
                mPopupWindow.showAtLocation(parent, gravity, x, y);
        }

        public void dismiss() {
            if (mPopupWindow != null) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        }

        /**
         * 在某一控件下方弹窗
         *
         * @param anchor
         */
        public void showAbove(View anchor) {
            showAbove(anchor, 0, 0);
        }

        /**
         * 在某一个控件上方弹窗
         *
         * @param anchor
         * @param xoff
         * @param yoff
         */
        public void showAbove(View anchor, int xoff, int yoff) {
            if (mPopupWindow != null) {
                mPopupWindow.setAnimationStyle(R.style.AnimationUpPopup);
                int[] location = new int[2];
                anchor.getLocationInWindow(location);
                mPopupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, location[0] + xoff, location[1] - mPopHeight + yoff);
            }
        }

        public void showFromBottom(View anchor) {
            if (mPopupWindow != null) {
                mPopupWindow.setAnimationStyle(R.style.AnimationFromButtom);
                mPopupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.BOTTOM, 0, 0);
            }
        }

        public void showFromTop(View anchor) {
            if (mPopupWindow != null) {
                mPopupWindow.setAnimationStyle(R.style.AnimationFromTop);
                mPopupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, 0, getStatusBarHeight());
            }
        }

        public void showInCenter(View anchor) {
            showAtLocation(anchor, Gravity.CENTER, 0, 0);
        }

        private int getStatusBarHeight() {
            return Math.round(25 * Resources.getSystem().getDisplayMetrics().density);
        }
    }
}