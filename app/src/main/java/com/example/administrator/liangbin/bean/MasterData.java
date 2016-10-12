package com.example.administrator.liangbin.bean;

/**
 * Created by Administrator on 2016/10/7.
 * 达人主界面实例
 */
public class MasterData {

    public String username;
    public String duty;
    public String orig;

    public MasterData(String username, String duty, String orig) {
        this.username = username;
        this.duty = duty;
        this.orig = orig;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }
}
