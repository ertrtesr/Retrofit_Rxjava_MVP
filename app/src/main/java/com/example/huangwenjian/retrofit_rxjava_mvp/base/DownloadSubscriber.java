package com.example.huangwenjian.retrofit_rxjava_mvp.base;

import com.example.huangwenjian.retrofit_rxjava_mvp.listener.DownloadProgressListener;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.FileUtils;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/8/30
 */
public class DownloadSubscriber extends Subscriber<ResponseBody> {
    private DownloadProgressListener mListener;
    private long mTotalSize;

    public DownloadSubscriber(DownloadProgressListener listener) {
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
            mTotalSize = responseBody.contentLength();              //得到content-length(文件大小);
            mListener.onStart(mTotalSize);

            String dir = FileUtils.getDir(FileUtils.DOWNLOAD_DIR);

//            ThreadManager.getSinglePool().execute(runn);
            saveFile(is, dir + "copy.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("下载开始了");
    }

    /**
     * 保存文件的方法
     *
     * @param is
     * @param targetPath
     * @throws IOException
     */
    public void saveFile(InputStream is, String targetPath) throws IOException {
        long currentLength = 0;
        File file = new File(targetPath);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buf = new byte[8 * 8];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
            currentLength = currentLength + len;
            mListener.onProgress(currentLength, mTotalSize);
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
