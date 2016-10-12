package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShopBrandData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 * 解析shop品牌json数据
 */
public class ParseShopBrand {

    public static List<ShopBrandData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<ShopBrandData> dataList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String brand_id = jsonObject2.getString("brand_id");
                String brand_name = jsonObject2.getString("brand_name");
                String brand_logo = jsonObject2.getString("brand_logo");
                ShopBrandData data = new ShopBrandData(brand_id,brand_name,brand_logo);
                dataList.add(data);
            }
            return dataList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
