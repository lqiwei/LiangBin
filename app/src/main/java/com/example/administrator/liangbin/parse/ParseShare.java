package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShareData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 * 解析分享界面数据
 */
public class ParseShare {

    public static List<ShareData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<ShareData> dataList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String goods_image = jsonObject2.getString("goods_image");
                ShareData data = new ShareData(goods_image);
                dataList.add(data);
            }
            return dataList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
