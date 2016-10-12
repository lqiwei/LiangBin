package com.example.administrator.liangbin.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.liangbin.R;

/**
 * Created by Administrator on 2016/9/27.
 * shop礼物Fragment
 */
public class ShopGiftFragment extends Fragment {

    private static ShopGiftFragment fragment;

    public static ShopGiftFragment getInstance(){
        if (fragment == null){
            fragment = new ShopGiftFragment();
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
        View view = inflater.inflate(R.layout.shop_gift_view,container,false);
        return view;
    }

}
