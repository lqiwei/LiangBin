package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.MasterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 * 解析达人界面GridView
 */
public class ParseMaster {

    public static List<MasterData> getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("items");
            int length = jsonArray.length();
            List<MasterData> dataList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String username = jsonObject2.getString("username");
                String duty = jsonObject2.getString("duty");
                JSONObject jsonObject3 = jsonObject2.getJSONObject("user_images");
                String orig = jsonObject3.getString("orig");
                MasterData data = new MasterData(username,duty,orig);
                dataList.add(data);
            }
            return dataList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
