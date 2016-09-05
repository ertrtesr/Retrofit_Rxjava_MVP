package com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
@Entity
public class ThreadInfo implements Serializable {
    @Id(autoincrement = false)
    private long id;
    private String url;
    private long start;
    private long end;
    private long finished;
    private static final long serialVersionUID = 1L;
    @Generated(hash = 607921762)
    public ThreadInfo(long id, String url, long start, long end, long finished) {
        this.id = id;
        this.url = url;
        this.start = start;
        this.end = end;
        this.finished = finished;
    }
    @Generated(hash = 930225280)
    public ThreadInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public long getStart() {
        return this.start;
    }
    public void setStart(long start) {
        this.start = start;
    }
    public long getEnd() {
        return this.end;
    }
    public void setEnd(long end) {
        this.end = end;
    }
    public long getFinished() {
        return this.finished;
    }
    public void setFinished(long finished) {
        this.finished = finished;
    }
}
