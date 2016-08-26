package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 可点击展开的textview
 * <p/>
 * 日期: on 16/8/25
 */
public class ExpandbleTextActivity extends Activity {
    @BindView(R.id.tv_expand)
    TextView mTv_expand;

    private int mMeasuredHeight;
    private boolean isOpen = true;      //textview状态
    private String mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandble_text);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.tv_expand)
    void click() {
        toggle(true);
    }

    private void init() {
        mData = "地方领导就放开手的骄傲的解放路口的设计方会计法律上飞机开始的减肥开始了大家发到空间发呆时离开房间是会计法律速度快放假了时间付款了及时的开发建设的开发阶段是咖啡加多少了房间多少款发动机" +
                "地方领导就放开手的骄傲的解放路口的设计方会计法律上飞机开始的减肥开始了大家发到空间发呆时离开房间是会计法律速度快放假了时间付款了及时的开发建设的开发阶段是咖啡加多少了房间多少款发动机" +
                "地方领导就放开手的骄傲的解放路口的设计方会计法律上飞机开始的减肥开始了大家发到空间发呆时离开房间是会计法律速度快放假了时间付款了及时的开发建设的开发阶段是咖啡加多少了房间多少款发动机" +
                "地方领导就放开手的骄傲的解放路口的设计方会计法律上飞机开始的减肥开始了大家发到空间发呆时离开房间是会计法律速度快放假了时间付款了及时的开发建设的开发阶段是咖啡加多少了房间多少款发动机" +
                "地方领导就放开手的骄傲的解放路口的设计方会计法律上飞机开始的减肥开始了大家发到空间发呆时离开房间是会计法律速度快放假了时间付款了及时的开发建设的开发阶段是咖啡加多少了房间多少款发动机";

        mTv_expand.setText(mData);
        mTv_expand.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mMeasuredHeight = mTv_expand.getMeasuredHeight();
                // 默认折叠
                toggle(false);
                // 如果不移除,一会高度变成7行的时候,mMeasuredHeight就会变
                mTv_expand.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private void toggle(boolean isAnimation) {
        if (isOpen) {   //打开-->折叠

            //mTvDes高度发生改变:应有的高度-->7行的高度
            int start = mMeasuredHeight;
            int end = getShortHeight(7, mData);
            if (isAnimation) {
                doAnimation(start, end);
            } else {
                mTv_expand.setHeight(end);
            }
        } else {    // 折叠-->展开
            int start = getShortHeight(7, mData);
            int end = mMeasuredHeight;
            if (isAnimation) {
                doAnimation(start, end);
            } else {
                mTv_expand.setHeight(end);
            }
        }

//        if (isAnimation) {// mTvDes正在折叠或者展开
//            if (isOpen) {
//                ObjectAnimator.ofFloat(mIvArrow, "rotation", 180, 0).start();
//            } else {
//                ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
//            }
//        }
        isOpen = !isOpen;
    }

    public void doAnimation(int start, int end) {
        ObjectAnimator animator = ObjectAnimator.ofInt(mTv_expand, "height", start, end);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {   // 动画开始

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {  // 动画重复

            }

            @Override
            public void onAnimationEnd(Animator arg0) { // 动画结束
                ViewParent parent = mTv_expand.getParent();
                while (true) {
                    parent = parent.getParent();
                    if (parent == null) {   // 已经没有父亲
                        break;
                    }
                    if (parent instanceof ScrollView) { // 已经找到
                        ((ScrollView) parent).fullScroll(View.FOCUS_DOWN);
                        break;
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {  // 动画取消

            }
        });
    }

    /**
     * @param i    指定行高
     * @param text 指定textView的内容
     * @return
     */
    private int getShortHeight(int i, String text) {
        //临时textView,只做测绘用
        TextView tempTextView = new TextView(UIUtils.getContext());
        tempTextView.setLines(7);
        tempTextView.setText(text);
        tempTextView.setTextSize(20);       //此处的textsize要跟布局中的保持一致,否则不能正确显示行数

        tempTextView.measure(0, 0);
        int measuredHeight = tempTextView.getMeasuredHeight();
        return measuredHeight;
    }
}
