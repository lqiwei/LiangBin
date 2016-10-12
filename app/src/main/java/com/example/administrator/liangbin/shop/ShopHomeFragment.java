package com.example.administrator.liangbin.shop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.adapter.ShopHomePagerAdapter;
import com.example.administrator.liangbin.bean.ShopHomeData;
import com.example.administrator.liangbin.bean.ShopHomeDataVP;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShopHome;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * shop首页Fragment
 */
public class ShopHomeFragment extends Fragment {

    private final String SHOP_HOME_URL = "http://mobile.iliangcang.com/goods/shopHome?app_key=Android&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";

    @BindView(R.id.shop_home_scroll_view)
    PullToRefreshScrollView scrollView;
    @BindView(R.id.shop_home_vp)
    ViewPager mViewPager;
    @BindView(R.id.shop_home_img1)
    ImageView imgOne;
    @BindView(R.id.shop_home_img2)
    ImageView imgTwo;
    @BindView(R.id.shop_home_img3)
    ImageView imgThree;
    @BindView(R.id.shop_home_img4)
    ImageView imgFour;
    @BindView(R.id.shop_home_img5)
    ImageView imgFive;
    @BindView(R.id.shop_home_img6)
    ImageView imgSix;
    @BindView(R.id.shop_home_iv2)
    ImageView imgSeven;

    private List<ShopHomeDataVP> vpList = new ArrayList<>();
    private List<View> viewList = new ArrayList<>();
    private ShopHomeData homeData;
    private static ShopHomeFragment fragment;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            homeData = (ShopHomeData) msg.obj;
            if (homeData != null){
                vpList = homeData.getDataVPs();
                loadView();
                init();
            }
        }
    };

    public static ShopHomeFragment getInstance(){
        if (fragment == null){
            fragment = new ShopHomeFragment();
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
        View view = inflater.inflate(R.layout.shop_home_page_view,container,false);
        ButterKnife.bind(this,view);

        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(getActivity())){
                    loadData();
                    //刷新完成后退出刷新模式
                    scrollView.onRefreshComplete();
                }else{
                    Toast.makeText(getActivity(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                    //刷新完成后退出刷新模式
                    scrollView.onRefreshComplete();
                }
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });

        return view;
    }

    /**
     * 加载ViewPager布局
     */
    private void loadView(){
        int length = vpList.size();
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(vpList.get(i).getPic_url()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(imageView);
        }
        ShopHomePagerAdapter adapter = new ShopHomePagerAdapter(viewList);
        mViewPager.setAdapter(adapter);
    }

    /**
     * 给控件赋值
     */
    private void init(){
        Picasso.with(getActivity()).load(homeData.getBottoms().get(0).getPic_url()).into(imgOne);
        Picasso.with(getActivity()).load(homeData.getBottoms().get(1).getPic_url()).into(imgTwo);
        Picasso.with(getActivity()).load(homeData.getBottoms().get(2).getPic_url()).into(imgThree);
        Picasso.with(getActivity()).load(homeData.getBottoms().get(3).getPic_url()).into(imgFour);
        Picasso.with(getActivity()).load(homeData.getBottoms().get(5).getPic_url()).into(imgFive);
        Picasso.with(getActivity()).load(homeData.getBottoms().get(6).getPic_url()).into(imgSix);
        Picasso.with(getActivity()).load(homeData.getBottoms().get(4).getPic_url()).into(imgSeven);
    }

    /**
     * 加载数据
     */
    private void loadData(){
        HttpUtils.getOkHttpNetData(SHOP_HOME_URL, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                if (str != null){
                    ShopHomeData data = ParseShopHome.getData(str);
                    Message message = new Message();
                    message.obj = data;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
