package com.example.administrator.liangbin.parse;

import com.example.administrator.liangbin.bean.ShopHomeBottom;
import com.example.administrator.liangbin.bean.ShopHomeData;
import com.example.administrator.liangbin.bean.ShopHomeDataVP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 * 解析shop首页json数据
 */
public class ParseShopHome {

    public static ShopHomeData getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONObject jsonObject2 = jsonObject1.getJSONObject("items");
            JSONArray jsonArray = jsonObject2.getJSONArray("slide");
            int length = jsonArray.length();
            List<ShopHomeDataVP> dataVPs = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                String pic_url_vp = jsonObject3.getString("pic_url");
                String topic_url_vp = jsonObject3.getString("topic_url");
                ShopHomeDataVP dataVP = new ShopHomeDataVP(pic_url_vp,topic_url_vp);
                dataVPs.add(dataVP);
            }

            List<ShopHomeBottom> bottoms = new ArrayList<>();
            JSONArray jsonArray1 = jsonObject2.getJSONArray("list");
            JSONObject jsonObject5 = jsonArray1.getJSONObject(0);

            JSONObject jsonObject6 = jsonObject5.getJSONObject("one");
            String pic_url_one = jsonObject6.getString("pic_url");
            String topic_url_one = jsonObject6.getString("topic_url");
            ShopHomeBottom bottom1 = new ShopHomeBottom(pic_url_one,topic_url_one);
            bottoms.add(bottom1);

            JSONObject jsonObject7 = jsonObject5.getJSONObject("two");
            String pic_url_two = jsonObject7.getString("pic_url");
            String topic_url_two = jsonObject7.getString("topic_url");
            ShopHomeBottom bottom2 = new ShopHomeBottom(pic_url_two,topic_url_two);
            bottoms.add(bottom2);

            JSONObject jsonObject8 = jsonObject5.getJSONObject("three");
            String pic_url_three = jsonObject8.getString("pic_url");
            String topic_url_three = jsonObject8.getString("topic_url");
            ShopHomeBottom bottom3 = new ShopHomeBottom(pic_url_three,topic_url_three);
            bottoms.add(bottom3);

            JSONObject jsonObject9 = jsonObject5.getJSONObject("four");
            String pic_url_four = jsonObject9.getString("pic_url");
            String topic_url_four = jsonObject9.getString("topic_url");
            ShopHomeBottom bottom5 = new ShopHomeBottom(pic_url_four,topic_url_four);
            bottoms.add(bottom5);

            JSONObject jsonObject10 = jsonArray1.getJSONObject(1);
            JSONObject jsonObject11 = jsonObject10.getJSONObject("one");
            String pic_url_five = jsonObject11.getString("pic_url");
            String topic_url_five = jsonObject11.getString("topic_url");
            ShopHomeBottom bottom6 = new ShopHomeBottom(pic_url_five,topic_url_five);
            bottoms.add(bottom6);

            JSONObject jsonObject12 = jsonArray1.getJSONObject(2);
            JSONObject jsonObject13 = jsonObject12.getJSONObject("one");
            String pic_url_six = jsonObject13.getString("pic_url");
            String topic_url_six = jsonObject13.getString("topic_url");
            ShopHomeBottom bottom7 = new ShopHomeBottom(pic_url_six,topic_url_six);
            bottoms.add(bottom7);

            JSONObject jsonObject15 = jsonObject12.getJSONObject("two");
            String pic_url_seven = jsonObject15.getString("pic_url");
            String topic_url_seven = jsonObject15.isNull("topic_url") ? null : jsonObject15.getString("topic_url");
            ShopHomeBottom bottom8 = new ShopHomeBottom(pic_url_seven,topic_url_seven);
            bottoms.add(bottom8);

            ShopHomeData data = new ShopHomeData(dataVPs,bottoms);
            return data;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
