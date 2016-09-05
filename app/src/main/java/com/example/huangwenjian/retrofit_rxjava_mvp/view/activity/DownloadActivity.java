package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIService;
import com.example.huangwenjian.retrofit_rxjava_mvp.api.APIWrapper;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.DownloadSubscriber;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.FileInfo;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.RetrofitManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/8/30
 */
public class DownloadActivity extends Activity {

    private String mBaseUrl = "";
    @BindView(R.id.btn_start_download)
    Button mBtn_start_download;

    @BindView(R.id.btn_pause_download)
    Button mBtn_pause_download;

    @BindView(R.id.btn_continue_download)
    Button mBtn_continue_download;
    private FileInfo mFileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        mFileInfo = new FileInfo();
        mFileInfo.setFileName("filename");
    }

    @OnClick(R.id.btn_start_download)
    void startDownload() {
        APIService service = RetrofitManager.getInstance(UIUtils.getContext(), "http://192.168.31.105:8080/").createService(APIService.class);
        APIWrapper.doDownload(service.downloadFile(), new DownloadSubscriber());
    }

    /**
     * 暂停下载,本质上是暂停向本地读写文件
     */
    @OnClick(R.id.btn_pause_download)
    void pauseDownload() {

    }

    @OnClick(R.id.btn_continue_download)
    void continueDownload() {

    }
}
