package com.example.administrator.liangbin.bean;

/**
 * Created by Administrator on 2016/9/30.
 * shop专题实例
 */
public class ShopSpecialData {

    public String topic_name;
    public String topic_url;
    public String cover_img;

    public ShopSpecialData(String topic_name, String topic_url, String cover_img) {
        this.topic_name = topic_name;
        this.topic_url = topic_url;
        this.cover_img = cover_img;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }
}
