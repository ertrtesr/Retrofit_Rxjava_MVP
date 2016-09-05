package com.example.huangwenjian.retrofit_rxjava_mvp.event;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 用于记录请求的地址,传递到需要的类中
 * <p/>
 * 时间: 16/9/4
 */
public class UrlEvent {
    private String url;

    public UrlEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
