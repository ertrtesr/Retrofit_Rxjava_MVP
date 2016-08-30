package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIService;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIWrapper;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.DownloadSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.listener.DownloadProgressListener;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.RetrofitManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/8/30
 */
public class DownloadActivity extends Activity {

    private String mBaseUrl = "";
    @BindView(R.id.btn_start_download)
    Button mBtn_start_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_download)
    void startDownload() {
        APIService service = RetrofitManager.getInstance(UIUtils.getContext(), "http://172.16.60.171:8080/").createService(APIService.class);
        APIWrapper.doDownload(service.download(), new DownloadSubscriber(new DownloadProgressListener() {
            @Override
            public void onStart(long totalSize) {
                System.out.println(totalSize);
            }

            @Override
            public void onProgress(long current, long total) {
//                System.out.println(current);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(String e) {

            }
        }));
    }
}
