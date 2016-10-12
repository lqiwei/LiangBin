package com.example.administrator.liangbin.share;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.adapter.ShareGridViewAdapter;
import com.example.administrator.liangbin.bean.ShareData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShare;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * 分享页面
 */
public class ShareFragment extends Fragment {

    private final String SHARE_URL_LEFT = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&count=10&page=";
    private final String SHARE_URL_RIGHT = "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&type=0&v=1.0";
    private int page = 1;
    private List<ShareData> datas = new ArrayList<>();
    private List<ShareData> totaList = new ArrayList<>();
    private ShareGridViewAdapter adapter;

    @BindView(R.id.share_grid_view)
    PullToRefreshGridView gridView;
    private static ShareFragment fragment;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            datas = (List<ShareData>) msg.obj;
            if (datas != null){
                totaList.addAll(totaList == null ? 0:totaList.size(),datas);
                adapter.notifyDataSetChanged();
            }
        }
    };

    public static ShareFragment getInstance(){
        if (fragment == null){
            fragment = new ShareFragment();
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
        View view = inflater.inflate(R.layout.fragment_share,container,false);
        ButterKnife.bind(this,view);
        adapter = new ShareGridViewAdapter(getActivity(),totaList);
        gridView.setAdapter(adapter);
        //设置可上拉和下拉
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置list_view上拉下拉监听
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                    //数据清空
                    totaList.clear();
                    page = 1;
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
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                    page++;
                    loadData();
                    //刷新完成后退出刷新模式
                    gridView.onRefreshComplete();
                }else{
                    Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                    //刷新完成后退出刷新模式
                    gridView.onRefreshComplete();
                }
            }
        });

        return view;
    }

    /**
     * 加载数据
     */
    private void loadData(){
        HttpUtils.getOkHttpNetData(SHARE_URL_LEFT + page + SHARE_URL_RIGHT, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                if (str != null){
                    List<ShareData> list = ParseShare.getData(str);
                    Message message = new Message();
                    message.obj = list;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
