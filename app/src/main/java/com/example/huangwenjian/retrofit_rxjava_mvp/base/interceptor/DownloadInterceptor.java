package com.example.huangwenjian.retrofit_rxjava_mvp.base.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/8/29
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadProgressListener progressListener;

    public DownloadInterceptor(DownloadProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), progressListener))
                .build();
    }

    private static class DownloadResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final DownloadProgressListener progressListener;
        private BufferedSource bufferedSource;

        public DownloadResponseBody(ResponseBody responseBody, DownloadProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();          //文件的类型
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();        //文件的总长度
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);       //已经读取的字节数 , super.read()即source.read()
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;      //读取到的字节数总和为之前读取的和本次读取的累加

                    if (null != progressListener) {
                        progressListener.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    }
                    return bytesRead;          //返回本次读取到的字节数
                }
            };
        }
    }

    public interface DownloadProgressListener {
        /**
         * @param current 已经下载的字节数
         * @param total   文件的总字节数
         * @param done    是否下载完成
         */
        void onProgress(long current, long total, boolean done);
    }
}
