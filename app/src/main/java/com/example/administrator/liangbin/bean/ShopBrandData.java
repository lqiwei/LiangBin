package com.example.administrator.liangbin.bean;

/**
 * Created by Administrator on 2016/9/29.
 * shop品牌实例
 */
public class ShopBrandData {

    public String brand_id;
    public String brand_name;
    public String brand_logo;

    public ShopBrandData(String brand_id, String brand_name, String brand_logo) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.brand_logo = brand_logo;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }
}
