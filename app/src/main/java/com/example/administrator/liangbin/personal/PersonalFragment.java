package com.example.administrator.liangbin.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.liangbin.R;

/**
 * Created by Administrator on 2016/9/27.
 * 个人页面
 */
public class PersonalFragment extends Fragment {

    private static PersonalFragment fragment;

    public static PersonalFragment getInstance(){
        if (fragment == null){
            fragment = new PersonalFragment();
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
        View view = inflater.inflate(R.layout.fragment_personal,container,false);

        return view;
    }
}
