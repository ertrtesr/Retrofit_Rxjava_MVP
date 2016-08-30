package com.example.huangwenjian.retrofit_rxjava_mvp.entity;

import com.example.huangwenjian.retrofit_rxjava_mvp.manager.DownloadManager;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/8/30
 */
public class DownloadInfo {
    public String savePath;
    public String downloadUrl;
    public int state = DownloadManager.STATE_UNDOWNLOAD; // 默认状态就是未下载
    public long max;
    public long current;
    public Runnable task;
}
