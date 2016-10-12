package com.example.administrator.liangbin.magazine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.adapter.MagazineExpandAdapter;
import com.example.administrator.liangbin.bean.MagazineChildData;
import com.example.administrator.liangbin.bean.MagazineData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseMagazine;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * 杂志页面
 */
public class MagazineFragment extends Fragment {

    private final String MAGAZINE_URL = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";

    @BindView(R.id.magazine_expand_list_view)
    PullToRefreshExpandableListView expandableListView;

    private static MagazineFragment fragment;
    private MagazineData magazineData;
    private MagazineExpandAdapter adapter;
    private List<String> strings = new ArrayList<>();
    private Map<String,List<MagazineChildData>> map = new HashMap<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            magazineData = (MagazineData) msg.obj;
            if (magazineData != null){
                strings = magazineData.getStringList();
                map = magazineData.getMap();
                Log.e("sssssss", "handleMessage: map"+map );
                Log.e("sssssss", "handleMessage: strings"+strings );
//                adapter.notifyDataSetChanged();
                adapter = new MagazineExpandAdapter(getActivity(),strings,map);
                //获取ExpandableListView并设置适配器
                expandableListView.getRefreshableView().setAdapter(adapter);
                //设置组的展开
                for (int i = 0; i < strings.size(); i++) {
                    expandableListView.getRefreshableView().expandGroup(i);
                }
                expandableListView.getRefreshableView().setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });
            }
        }
    };

    public static MagazineFragment getInstance(){
        if (fragment == null){
            fragment = new MagazineFragment();
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
        View view = inflater.inflate(R.layout.fragment_magazine,container,false);
        ButterKnife.bind(this,view);
//        adapter = new MagazineExpandAdapter(getActivity(),strings,map);
//        //获取ExpandableListView并设置适配器
//        expandableListView.getRefreshableView().setAdapter(adapter);
        //设置可上拉和下拉
        expandableListView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置list_view上拉下拉监听
        expandableListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ExpandableListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                //刷新完成后退出刷新模式
                expandableListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                //刷新完成后退出刷新模式
                expandableListView.onRefreshComplete();
            }
        });

        return view;
    }

    /**
     * 加载数据
     */
    private void loadData(){
        Log.e("cccccccccccccc", "loadData: ");
        HttpUtils.getOkHttpNetData(MAGAZINE_URL, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                Log.e("aaaaaaaaaaaaaa", "setOnJsonLisiter: str"+str );
                if (str != null){
                    MagazineData data = ParseMagazine.getData(str);
                    Message message = new Message();
                    message.obj = data;
                    Log.e("bbbbbbbbbbbb", "message: data"+data.getStringList() );
                    handler.sendMessage(message);
                }
            }
        });
    }
}
