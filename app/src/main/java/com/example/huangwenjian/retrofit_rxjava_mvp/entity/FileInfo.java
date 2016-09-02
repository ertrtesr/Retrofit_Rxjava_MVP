package com.example.huangwenjian.retrofit_rxjava_mvp.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class FileInfo implements Serializable {
    private int id;                           //文件id
    private String fileName;                  //文件名
    private long downloadedLength;            //已经下载的长度
    private long totalLength;                 //文件总大小
    private int type;                         //文件类型
    private String savePath;                  //文件存储位置

    public FileInfo() {
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

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public long getDownloadedLength() {
        return downloadedLength;
    }

    public void setDownloadedLength(long downloadedLength) {
        this.downloadedLength = downloadedLength;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", totalLength=" + totalLength +
                ", downloadedLength=" + downloadedLength +
                ", type=" + type +
                ", savePath='" + savePath + '\'' +
                '}';
    }
}
