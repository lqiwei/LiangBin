package com.example.administrator.liangbin.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/9/29.
 * 网络连接工具类
 */
public class IsNetWorkUtils {

    /**
     *  检测是否有网络连接
     */
    public static boolean geCurrentNetWorkIsConnected(Context context){
        //获取连接管理类的对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
        for (int i = 0; i < infos.length; i++) {
            NetworkInfo info = infos[i];
            if (info.isConnected()){
                return true;
            }
        }
        return false;
    }
}
