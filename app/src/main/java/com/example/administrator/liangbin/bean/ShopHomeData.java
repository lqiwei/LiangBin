package com.example.administrator.liangbin.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 * shop首页实例
 */
public class ShopHomeData {

    public List<ShopHomeDataVP> dataVPs;
    public List<ShopHomeBottom> bottoms;

    public ShopHomeData(List<ShopHomeDataVP> dataVPs, List<ShopHomeBottom> bottoms) {
        this.dataVPs = dataVPs;
        this.bottoms = bottoms;
    }

    public List<ShopHomeDataVP> getDataVPs() {
        return dataVPs;
    }

    public void setDataVPs(List<ShopHomeDataVP> dataVPs) {
        this.dataVPs = dataVPs;
    }

    public List<ShopHomeBottom> getBottoms() {
        return bottoms;
    }

    public void setBottoms(List<ShopHomeBottom> bottoms) {
        this.bottoms = bottoms;
    }
}
