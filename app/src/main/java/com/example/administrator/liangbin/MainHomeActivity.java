package com.example.administrator.liangbin;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.liangbin.magazine.MagazineFragment;
import com.example.administrator.liangbin.master.MasterFragment;
import com.example.administrator.liangbin.personal.PersonalFragment;
import com.example.administrator.liangbin.share.ShareFragment;
import com.example.administrator.liangbin.shop.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity {

    @BindView(R.id.home_bottom_rg)
    RadioGroup homeRg;

    @BindView(R.id.home_bottom_shop_rb)
    RadioButton homeShopRb;

    @BindView(R.id.home_bottom_magazine_rb)
    RadioButton homeMagazineRb;

    @BindView(R.id.home_bottom_master_rb)
    RadioButton homeMasterRb;

    @BindView(R.id.home_bottom_share_rb)
    RadioButton homeShareRb;

    @BindView(R.id.home_bottom_personal_rb)
    RadioButton homePersonalRb;

    private FragmentManager manager;
    private ShopFragment shopFragment;
    private MagazineFragment magazineFragment;
    private MasterFragment masterFragment;
    private ShareFragment shareFragment;
    private PersonalFragment personalFragment;
    private Fragment mCurrentShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();

        initFragment();
        initView();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        shopFragment = ShopFragment.getInstance();
        magazineFragment = MagazineFragment.getInstance();
        masterFragment = MasterFragment.getInstance();
        shareFragment = ShareFragment.getInstance();
        personalFragment = PersonalFragment.getInstance();
        ctrlFragment(shopFragment);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        homeRg.check(R.id.home_bottom_shop_rb);
        homeRg.setOnCheckedChangeListener(listener);
        homeShopRb.setTextColor(Color.parseColor("#ffffff"));
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTextColor();
            switch (checkedId){
                case R.id.home_bottom_shop_rb:
                    ctrlFragment(shopFragment);
                    homeShopRb.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case R.id.home_bottom_magazine_rb:
                    ctrlFragment(magazineFragment);
                    homeMagazineRb.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case R.id.home_bottom_master_rb:
                    ctrlFragment(masterFragment);
                    homeMasterRb.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case R.id.home_bottom_share_rb:
                    ctrlFragment(shareFragment);
                    homeShareRb.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case R.id.home_bottom_personal_rb:
                    ctrlFragment(personalFragment);
                    homePersonalRb.setTextColor(Color.parseColor("#ffffff"));
                    break;
            }
        }
    };

    /**
     * 设置字体默认颜色
     */
    private void setTextColor(){
        homeShopRb.setTextColor(Color.parseColor("#999999"));
        homeMagazineRb.setTextColor(Color.parseColor("#999999"));
        homeMasterRb.setTextColor(Color.parseColor("#999999"));
        homeShareRb.setTextColor(Color.parseColor("#999999"));
        homePersonalRb.setTextColor(Color.parseColor("#999999"));
    }

    /**
     * 当前显示的Fragment
     */
    private void ctrlFragment(Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()){
            transaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()){
            transaction.add(R.id.home_fragment_layout,fragment);
        }else{
            transaction.show(fragment);
        }
        transaction.commit();
        mCurrentShowFragment = fragment;
    }
}
