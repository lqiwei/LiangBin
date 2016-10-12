package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShopSpecialData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 * 解析shop专题json数据
 */
public class ParseShopSpecial {

    public static List<ShopSpecialData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<ShopSpecialData> specialDatas = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String topic_name = jsonObject2.getString("topic_name");
                String topic_url = jsonObject2.getString("topic_url");
                String cover_img = jsonObject2.getString("cover_img");
                ShopSpecialData data = new ShopSpecialData(topic_name,topic_url,cover_img);
                specialDatas.add(data);
            }
            return specialDatas;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
