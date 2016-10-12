package com.example.administrator.liangbin.bean;

/**
 * Created by Administrator on 2016/10/12.
 * 搜索商品界面实例
 */
public class ShopSearchData {

    public String goods_image;
    public String goods_name;
    public String price;
    public String like_count;

    public ShopSearchData(String goods_image, String goods_name, String price, String like_count) {
        this.goods_image = goods_image;
        this.goods_name = goods_name;
        this.price = price;
        this.like_count = like_count;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }
}
