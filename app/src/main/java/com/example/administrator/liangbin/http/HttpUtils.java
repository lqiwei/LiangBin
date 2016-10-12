package com.example.administrator.liangbin.http;

import com.example.administrator.liangbin.callback.OnJsonLisiter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/28.
 * 网络请求工具类
 */
public class HttpUtils {

    private static OnJsonLisiter lisiter = null;

    /**
     * OkHttp_GET
     */
    public static void getOkHttpNetData(String path, OnJsonLisiter onJsonLisiter){
        lisiter = onJsonLisiter;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(path).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null){
                    lisiter.setOnJsonLisiter(response.body().string());
                }
            }
        });
    }

    /**
     * HttpURLConnection_GET
     */
    public static String getHttpURLNetData(String path){
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*1000);
            connection.connect();
            if (connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                StringBuilder builder = new StringBuilder();
                while ((len = inputStream.read(buf)) != -1){
                    builder.append(new String(buf,0,len));
                }
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用RxJava进行异步操作
     */
    public static void getRxJavaNetData(final String path,OnJsonLisiter onJsonLisiter){

        lisiter = onJsonLisiter;
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //进行网络请求
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    if (connection.getResponseCode()==200) {
                        InputStream is = connection.getInputStream();
                        int len = 0;
                        byte[] buf = new byte[1024];
                        StringBuilder builder = new StringBuilder();
                        while ((len = is.read(buf)) != -1){
                            builder.append(new String(buf,0,len));
                        }
                        subscriber.onNext(builder.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())//运行在主线程
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (s != null){
                            lisiter.setOnJsonLisiter(s);
                        }
                    }
                });
    }

}
