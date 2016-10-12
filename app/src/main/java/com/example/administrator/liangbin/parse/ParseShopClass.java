package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShopClassData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 * 解析shop分类json数据
 */
public class ParseShopClass {

    public static List<ShopClassData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<ShopClassData> dataList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String new_cover_img = jsonObject2.getString("new_cover_img");
                ShopClassData data = new ShopClassData(new_cover_img);
                dataList.add(data);
            }
            return dataList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
