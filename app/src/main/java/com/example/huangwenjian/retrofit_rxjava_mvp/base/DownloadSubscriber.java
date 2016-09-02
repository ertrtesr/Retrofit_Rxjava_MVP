package com.example.huangwenjian.retrofit_rxjava_mvp.base;

import android.content.Context;
import android.content.Intent;

import com.example.huangwenjian.retrofit_rxjava_mvp.entity.FileInfo;
import com.example.huangwenjian.retrofit_rxjava_mvp.listener.DownloadProgressListener;
import com.example.huangwenjian.retrofit_rxjava_mvp.service.DownloadService;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.IOUtils;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/8/30
 */
public class DownloadSubscriber extends Subscriber<ResponseBody> {
    private DownloadProgressListener mListener;
    private long mTotalLength;
    private Context mContext;

    public DownloadSubscriber() {
    }

    public DownloadSubscriber(DownloadProgressListener listener) {
        this.mListener = listener;
    }

    public void setDownloadProgressListener(DownloadProgressListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        System.out.println(Thread.currentThread().getName());
        try {
            InputStream is = responseBody.byteStream();             //得到数据流
            MediaType mediaType = responseBody.contentType();       //得到文件的类型
            mTotalLength = responseBody.contentLength();              //得到content-length(文件大小);

            //设置FileInfo
            FileInfo fileInfo = new FileInfo();
            fileInfo.setType(0);
            fileInfo.setTotalLength(mTotalLength);
            fileInfo.setFileName("11.jpg");

            Intent intent = new Intent(mContext, DownloadService.class);       //开启一个Service用于在后台下载文件
            intent.setAction(DownloadService.ACTION_START);
            intent.putExtra("fileInfo", fileInfo);
            mContext.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mContext = UIUtils.getContext();
        System.out.println("下载开始了");
    }

    /**
     * 保存文件的方法
     *
     * @param is
     * @param targetFile
     * @throws IOException
     */
    public void saveFile(InputStream is, File targetFile) throws IOException {
        long currentLength = 0;
        FileOutputStream fos = new FileOutputStream(targetFile);
        byte[] buf = new byte[8 * 8];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
            currentLength = currentLength + len;
            mListener.onProgress(currentLength, mTotalLength);
            fos.flush();
        }
        IOUtils.close(fos);
    }

    class DownloadTask implements Runnable {
        public DownloadTask() {

        }

        @Override
        public void run() {

        }
    }
}
