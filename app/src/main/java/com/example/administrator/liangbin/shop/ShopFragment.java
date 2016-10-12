package com.example.administrator.liangbin.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.liangbin.MainHomeActivity;
import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.RegisterActivity;
import com.example.administrator.liangbin.ShopCarActivity;
import com.example.administrator.liangbin.adapter.ShopPagerAdapater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 * 商店页面
 */
public class ShopFragment extends Fragment {

    private static ShopFragment fragment;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @BindView(R.id.shop_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.shop_view_pager)
    ViewPager viewPager;
    @BindView(R.id.shop_fragment_top_right_iv)
    ImageView imageView;

    /**
     * 监听是否进行了注册
     */
    private boolean isRegister;

    public static ShopFragment getInstance(){
        if (fragment == null){
            fragment = new ShopFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop,container,false);
        ButterKnife.bind(this,view);
        initData();
        //设置购物车图标点击监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRegister){
                    isRegister = true;
                    Intent intent = new Intent(getActivity(),RegisterActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(),ShopCarActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    /**
     * 初始化数据
     */
    private void initData(){
        titles.add("分类");
        titles.add("品牌");
        titles.add("首页");
        titles.add("专题");
        titles.add("礼物");
        fragments.add(ShopClassificationFragment.getInstance());
        fragments.add(ShopBrandFragment.getInstance());
        fragments.add(ShopHomeFragment.getInstance());
        fragments.add(ShopSpecialFragment.getInstance());
        fragments.add(ShopGiftFragment.getInstance());
        ShopPagerAdapater adapater = new ShopPagerAdapater(
                getChildFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapater);
        //设置字体颜色
        tabLayout.setTabTextColors(Color.parseColor("#999999"),Color.parseColor("#ffffff"));
        //设置指示器颜色
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        //设置背景颜色
        tabLayout.setBackgroundColor(Color.parseColor("#F5000000"));
        //关联TabLayout和ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
}
