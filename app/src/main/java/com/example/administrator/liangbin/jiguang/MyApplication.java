package com.example.administrator.liangbin.jiguang;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/9/29.
 * 极光
 * AppKey:c512ef63e238d42de2f0b14d
 * Master Secret:069c796a888e030bf36ba2d6
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
    }
}
