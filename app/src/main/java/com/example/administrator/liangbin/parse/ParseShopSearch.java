package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShopSearchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 * 解析搜索商品界面数据
 */
public class ParseShopSearch {

    public static List<ShopSearchData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<ShopSearchData> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String goods_image = jsonObject2.getString("goods_image");
                String goods_name = jsonObject2.getString("goods_name");
                String price = jsonObject2.getString("price");
                String like_count = jsonObject2.getString("like_count");
                ShopSearchData data = new ShopSearchData(
                        goods_image,goods_name,price,like_count);
                list.add(data);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
