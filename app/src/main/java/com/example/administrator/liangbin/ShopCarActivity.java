package com.example.administrator.liangbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.liangbin.adapter.ShopCarListViewAdapter;
import com.example.administrator.liangbin.bean.ShopClassDetailData;
import com.example.administrator.liangbin.db.DBManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商店购物车Activity
 */
public class ShopCarActivity extends AppCompatActivity {

    @BindView(R.id.shop_car_list_view)
    ListView listView;
    @BindView(R.id.shop_car_bottom_checked_iv)
    ImageView selectBothImg;

    private boolean isSelectBoth = false;

    private ShopCarListViewAdapter adapter;
    private List<ShopClassDetailData> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);

        loadData();
    }

    /**
     * 查询数据库以获取购物车商品列表数据源
     */
    private void loadData(){
        mList = DBManager.getInstance(this).queryData();
        initView();
    }

    private void initView() {
        adapter = new ShopCarListViewAdapter(mList,this);
        listView.setAdapter(adapter);

        //设置全选按钮监听
        selectBothImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectBoth){
                    isSelectBoth = true;
                    selectBothImg.setImageResource(R.drawable.ic_cart_selected);
                    adapter.setSelectBoth(isSelectBoth);
                    adapter.notifyDataSetChanged();
                }else{
                    isSelectBoth = false;
                    selectBothImg.setImageResource(R.drawable.ic_cart_unselected);
                    adapter.setSelectBoth(isSelectBoth);
                    adapter.notifyDataSetChanged();
                }
            }
        });
//
//        //设置listView的item点击监听
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (isSelectBoth){
//                    isSelectBoth = false;
//                    adapter.setSelect(isSelectBoth,position);
//                    Log.e("========", "onItemClick: "+isSelectBoth+position);
//                    adapter.notifyDataSetChanged();
//                }else{
//                    isSelectBoth = true;
//                    adapter.setSelect(isSelectBoth,position);
//                    Log.e("========", "onItemClick: "+isSelectBoth+position);
//                    adapter.notifyDataSetChanged();
//                }
//                String s = mList.get(position).getPrice();
//                Log.e("========", "onItemClick: "+s);
//            }
//        });
    }
}
