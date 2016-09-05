package com.example.huangwenjian.retrofit_rxjava_mvp.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class FileInfo implements Serializable {
    private int id;                           //文件id
    private String url;                       //url地址
    private String fileName;                  //文件名
    private long length;                       //文件总大小
    private long finished;
    private String downloadPath;              //文件存储位置

    public FileInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", length=" + length +
                ", finished=" + finished +
                ", downloadPath='" + downloadPath + '\'' +
                '}';

    }
}
