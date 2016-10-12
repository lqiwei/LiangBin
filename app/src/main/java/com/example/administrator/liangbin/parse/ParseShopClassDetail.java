package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShopClassDetailData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 * 解析shop分类详情列表数据
 */
public class ParseShopClassDetail {

    public static List<ShopClassDetailData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<ShopClassDetailData> dataList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String goods_image = jsonObject2.getString("goods_image");
                String goods_name = jsonObject2.getString("goods_name");
                JSONObject jsonObject3 = jsonObject2.getJSONObject("brand_info");
                String brand_name = jsonObject3.getString("brand_name");
                String price = jsonObject2.getString("price");
                String like_count = jsonObject2.getString("like_count");
                ShopClassDetailData data = new ShopClassDetailData(goods_image,
                        goods_name,brand_name,price,like_count);
                dataList.add(data);
            }
            return dataList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
