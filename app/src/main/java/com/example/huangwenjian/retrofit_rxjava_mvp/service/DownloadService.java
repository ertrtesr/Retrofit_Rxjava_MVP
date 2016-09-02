package com.example.huangwenjian.retrofit_rxjava_mvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.huangwenjian.retrofit_rxjava_mvp.entity.FileInfo;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class DownloadService extends Service {
    public static final String ACTION_START = "Start";
    public static final String ACTION_CONTINUE = "Continue";
    public static final String ACTION_PAUSE = "Pause";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ACTION_START.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            System.out.println(fileInfo.toString());
        } else if (ACTION_PAUSE.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            System.out.println(fileInfo.toString());
        } else if (ACTION_CONTINUE.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            System.out.println(fileInfo.toString());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void downloadFile(FileInfo fileInfo) {

    }
}
