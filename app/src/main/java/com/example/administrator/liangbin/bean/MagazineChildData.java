package com.example.administrator.liangbin.bean;

/**
 * Created by Administrator on 2016/10/8.
 * 杂志子成员实例
 */
public class MagazineChildData {

    public String topic_name;
    public String cat_name;
    public String cover_img_new;

    public MagazineChildData(String topic_name, String cat_name, String cover_img_new) {
        this.topic_name = topic_name;
        this.cat_name = cat_name;
        this.cover_img_new = cover_img_new;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCover_img_new() {
        return cover_img_new;
    }

    public void setCover_img_new(String cover_img_new) {
        this.cover_img_new = cover_img_new;
    }
}
