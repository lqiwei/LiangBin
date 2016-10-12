package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.MagazineChildData;
import com.example.administrator.liangbin.bean.MagazineData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/8.
 * 解析杂志主界面数据
 */
public class ParseMagazine {

    public static MagazineData getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONObject jsonObject2 = jsonObject1.getJSONObject("items");
            JSONArray jsonArray = jsonObject2.getJSONArray("keys");
            List<String> stringList = new ArrayList<>();//组的集合
            int length1 = jsonArray.length();
            for (int i = 0; i < length1; i++) {
                String s = jsonArray.getString(i);
                stringList.add(s);
            }
            JSONObject jsonObject3 = jsonObject2.getJSONObject("infos");
            int length2 = stringList.size();
            Map<String,List<MagazineChildData>> map = new HashMap<>();
            for (int i = 0; i < length2; i++) {
                List<MagazineChildData> childDataList = new ArrayList<>();
                JSONArray jsonArray1 = jsonObject3.getJSONArray(stringList.get(i));
                int length3 = jsonArray1.length();
                for (int j = 0; j < length3; j++) {
                    JSONObject jsonObject5 = jsonArray1.getJSONObject(j);
                    String topic_name = jsonObject5.getString("topic_name");
                    String cat_name = jsonObject5.getString("cat_name");
                    String cover_img_new = jsonObject5.getString("cover_img_new");
                    MagazineChildData childData = new MagazineChildData(topic_name,cat_name,cover_img_new);
                    childDataList.add(childData);
                }
                map.put(stringList.get(i),childDataList);
            }
            MagazineData data = new MagazineData(stringList,map);
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
