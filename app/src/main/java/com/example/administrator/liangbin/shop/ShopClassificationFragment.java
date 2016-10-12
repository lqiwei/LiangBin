package com.example.administrator.liangbin.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.RegisterActivity;
import com.example.administrator.liangbin.ShopCarActivity;
import com.example.administrator.liangbin.ShopClassificationDetailActivity;
import com.example.administrator.liangbin.adapter.ShopClassGridViewAdapter;
import com.example.administrator.liangbin.bean.ShopClassData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShopClass;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * shop分类Fragment
 */
public class ShopClassificationFragment extends Fragment {

    private final String SHOP_CLASS_URL = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    private List<ShopClassData> dataList = new ArrayList<>();

    @BindView(R.id.shop_class_grid_view)
    PullToRefreshGridView gridView;


    private FragmentManager fragmentManager;

    private static ShopClassificationFragment fragment;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dataList = (List<ShopClassData>) msg.obj;
            if (dataList != null){
                ShopClassGridViewAdapter adapter = new ShopClassGridViewAdapter(getActivity(),dataList);
                gridView.setAdapter(adapter);
            }
        }
    };

    public static ShopClassificationFragment getInstance(){
        if (fragment == null){
            fragment = new ShopClassificationFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getChildFragmentManager();
        if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
            loadData();
        }else{
            Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_classification_view,container,false);
        ButterKnife.bind(this,view);
        //设置grid_view下拉刷新监听
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                    loadData();
                    //刷新完成后退出刷新模式
                    gridView.onRefreshComplete();
                }else{
                    Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                    //刷新完成后退出刷新模式
                    gridView.onRefreshComplete();
                }
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

            }
        });
        //设置grid_view的item点击监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShopClassificationDetailActivity.class);
                intent.putExtra("page",position+1);
                startActivity(intent);
            }
        });

        return view;
    }

    /**
     * 加载数据
     */
    private void loadData(){
        HttpUtils.getOkHttpNetData(SHOP_CLASS_URL, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                Log.e("ssssssssss", "setOnJsonLisiter: "+str );
                if (str != null){
                    List<ShopClassData> datas = ParseShopClass.getData(str);
                    Message message = new Message();
                    message.obj = datas;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
