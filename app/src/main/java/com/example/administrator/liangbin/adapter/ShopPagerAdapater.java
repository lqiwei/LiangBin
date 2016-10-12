package com.example.administrator.liangbin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 * shop界面viewpager适配器
 */
public class ShopPagerAdapater extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    public ShopPagerAdapater(FragmentManager fm,List<Fragment> fragments,List<String> strings) {
        super(fm);
        this.fragments = fragments;
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
