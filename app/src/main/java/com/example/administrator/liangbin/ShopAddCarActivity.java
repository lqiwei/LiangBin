package com.example.administrator.liangbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.liangbin.bean.ShopClassDetailData;
import com.example.administrator.liangbin.db.DBManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * shop商品加入购物车界面Activity
 */
public class ShopAddCarActivity extends AppCompatActivity {

    @BindView(R.id.shop_class_detail_car_back)
    ImageView backImg;
    private ShopClassDetailData data;
    private String url;
    private String name;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_add_car);
        ButterKnife.bind(this);

        getIntentData();
        initView();
    }

    /**
     * 接收传递过来的数据
     */
    private void getIntentData(){
        data = (ShopClassDetailData) getIntent().getSerializableExtra("data");
        url = data.getGoods_image();
        name = data.getGoods_name();
        price = data.getPrice();
    }

    public void addShopCar(View v){
        //将对象加入数据库
        ShopClassDetailData detailData = new ShopClassDetailData();
        detailData.goods_image = url;
        detailData.goods_name = name;
        detailData.price = price;
        DBManager.getInstance(this).insertData(this, detailData);
    }

    /**
     * 返回上层界面
     */
    private void initView(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
