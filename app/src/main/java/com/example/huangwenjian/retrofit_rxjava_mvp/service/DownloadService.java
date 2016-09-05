package com.example.huangwenjian.retrofit_rxjava_mvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.huangwenjian.retrofit_rxjava_mvp.entity.FileInfo;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.ThreadManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.FileUtils;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class DownloadService extends Service {
    public static final String ACTION_START = "Start";
    public static final String ACTION_CONTINUE = "Continue";
    public static final String ACTION_PAUSE = "Pause";
    private FileInfo mFileInfo;

    public static final int MSG_INIT = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_INIT:
                    LogUtils.i(mFileInfo.toString());
                    break;
                default:
                    break;
            }
        }
    };

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
            mFileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
        } else if (ACTION_PAUSE.equals(intent.getAction())) {
            mFileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
        } else if (ACTION_CONTINUE.equals(intent.getAction())) {
            mFileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
        }

        ThreadManager.getSinglePool().execute(new DownloadTask());
        return super.onStartCommand(intent, flags, startId);
    }

    class DownloadTask implements Runnable {
        public DownloadTask() {

        }

        @Override
        public void run() {
            downloadFile(mFileInfo);
        }
    }

    public void downloadFile(FileInfo fileInfo) {
        RandomAccessFile raf = null;
        //在本地创建下载文件
        File downloadFile = new File(FileUtils.getDownloadDir(), fileInfo.getFileName());
        try {
            raf = new RandomAccessFile(downloadFile, "rwd");

            //设置文件长度
            raf.setLength(fileInfo.getLength());
            mHandler.obtainMessage(MSG_INIT, mFileInfo).sendToTarget();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
