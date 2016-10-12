package com.example.administrator.liangbin.shop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.adapter.ShopBrandListAdapter;
import com.example.administrator.liangbin.bean.ShopBrandData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShopBrand;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * shop品牌Fragment
 */
public class ShopBrandFragment extends Fragment {

    private int page = 1;
    private String url1 = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=";
    private String url2 = "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";

    @BindView(R.id.shop_brand_list_view)
    PullToRefreshListView listView;

    private static ShopBrandFragment fragment;
    private List<ShopBrandData> dataList = new ArrayList<>();
    private List<ShopBrandData> totaList = new ArrayList<>();
    private ShopBrandListAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dataList = (List<ShopBrandData>) msg.obj;
            if (dataList != null){
                totaList.addAll(totaList == null ? 0:totaList.size(),dataList);
                adapter.notifyDataSetChanged();
            }
        }
    };

    public static ShopBrandFragment getInstance(){
        if (fragment == null){
            fragment = new ShopBrandFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
            loadData();
        }else{
            Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_brand_view,container,false);
        ButterKnife.bind(this,view);
        adapter = new ShopBrandListAdapter(getActivity(),totaList);
        listView.setAdapter(adapter);
        //设置可上拉和下拉
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置list_view上拉下拉监听
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                    //数据清空
                    totaList.clear();
                    page = 1;
                    loadData();
                    //刷新完成后退出刷新模式
                    listView.onRefreshComplete();
                }else{
                    Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                    //刷新完成后退出刷新模式
                    listView.onRefreshComplete();
                }
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                    page++;
                    loadData();
                    //刷新完成后退出刷新模式
                    listView.onRefreshComplete();
                }else{
                    Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                    //刷新完成后退出刷新模式
                    listView.onRefreshComplete();
                }
            }
        });
        return view;
    }

    /**
     * 加载网络数据
     */
    private void loadData(){
        HttpUtils.getOkHttpNetData(url1 + page + url2, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                if (str != null){
                    List<ShopBrandData> list = ParseShopBrand.getData(str);
                    Message message = new Message();
                    message.obj = list;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
