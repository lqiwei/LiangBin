package com.example.administrator.liangbin.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/8.
 * 杂志主界面实例
 */
public class MagazineData {

    public List<String> stringList;
    public Map<String,List<MagazineChildData>> map;

    public MagazineData(List<String> stringList, Map<String, List<MagazineChildData>> map) {
        this.stringList = stringList;
        this.map = map;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, List<MagazineChildData>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<MagazineChildData>> map) {
        this.map = map;
    }
}
