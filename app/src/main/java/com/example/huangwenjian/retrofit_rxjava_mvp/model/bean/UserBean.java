package com.example.huangwenjian.retrofit_rxjava_mvp.model.bean;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/23
 */
public class UserBean {

    public SogaBean soga;
    public String status;
    public int versionCode;

    public static class SogaBean {
        public String name;
        public String address;
    }
}
