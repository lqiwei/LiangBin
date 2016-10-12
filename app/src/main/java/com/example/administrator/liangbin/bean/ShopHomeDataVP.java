package com.example.administrator.liangbin.bean;

/**
 * Created by Administrator on 2016/9/28.
 * shop首页VP实例
 */
public class ShopHomeDataVP {

    public String pic_url;
    public String topic_url;

    public ShopHomeDataVP(String pic_url, String topic_url) {
        this.pic_url = pic_url;
        this.topic_url = topic_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }
}
