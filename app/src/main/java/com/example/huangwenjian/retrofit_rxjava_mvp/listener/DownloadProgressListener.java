package com.example.huangwenjian.retrofit_rxjava_mvp.listener;

public interface DownloadProgressListener {
    void onStart(long totalSize);

    /**
     * @param current 已经下载的字节数
     * @param total   文件的总字节数
     */
    void onProgress(long current, long total);

    void onFinish();

    void onFailure(String e);
}