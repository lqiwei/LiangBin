package com.example.administrator.liangbin.shop;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.adapter.ShopSpecialListAdapter;
import com.example.administrator.liangbin.bean.ShopSpecialData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShopSpecial;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * shop专题Fragment
 */
public class ShopSpecialFragment extends Fragment {

    private int page = 1;
    private final String SHOP_SPECIAL_URL1 = "http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=";
    private final String SHOP_SPECIAL_URL2 = "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";

    private String url = "http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=360p&line=1&is_gif=0\n";
    private String URL_VIDEO = Environment.getExternalStorageDirectory()+"/VID_20160207_193939.mp4";

    @BindView(R.id.shop_special_list_view)
    PullToRefreshListView listView;
    private List<ShopSpecialData> specialDatas = new ArrayList<>();
    private List<ShopSpecialData> totaList = new ArrayList<>();

    private ShopSpecialListAdapter adapter;
    private static ShopSpecialFragment fragment;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            specialDatas = (List<ShopSpecialData>) msg.obj;
            if (specialDatas != null){
                totaList.addAll(totaList==null ? 0:totaList.size(),specialDatas);
                adapter.notifyDataSetChanged();
            }
        }
    };

    public static ShopSpecialFragment getInstance(){
        if (fragment == null){
            fragment = new ShopSpecialFragment();
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
        View view = inflater.inflate(R.layout.shop_special_view,container,false);
        ButterKnife.bind(this,view);
        initView();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView(){
        adapter = new ShopSpecialListAdapter(getActivity(),totaList);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.shop_special_listview_header,null,false);
        VideoView videoView = (VideoView) view.findViewById(R.id.shop_special_video_view);
        //添加VideoView到头部视图
        listView.getRefreshableView().addHeaderView(view);
        listView.setAdapter(adapter);
        //设置可上拉和下拉
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置listview上下拉监听
        listView.setOnRefreshListener(onRefreshListener2);
        //设置分割线颜色和高度
        listView.getRefreshableView().setDivider(new ColorDrawable(Color.RED));
        listView.getRefreshableView().setDividerHeight(15);
        //播放视频
//        videoView.setVideoURI(Uri.parse(url));
        videoView.setVideoPath(URL_VIDEO);
        MediaController controller = new MediaController(getActivity());
        videoView.setMediaController(controller);
        videoView.start();
    }

    /**
     * listview上下拉监听器
     */
    private PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                //数据清空
                totaList.clear();
                page = 1;
                loadData();
            }else{
                Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                //刷新完成后退出刷新模式
                listView.onRefreshComplete();
            }
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
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
    };

    /**
     * 加载数据
     */
    private void loadData(){
        HttpUtils.getOkHttpNetData(SHOP_SPECIAL_URL1 + page + SHOP_SPECIAL_URL2, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                if (str != null){
                    List<ShopSpecialData> list = ParseShopSpecial.getData(str);
                    Message message = new Message();
                    message.obj = list;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
