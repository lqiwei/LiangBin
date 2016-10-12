package com.example.administrator.liangbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.liangbin.adapter.ShopClassDetailAdapter;
import com.example.administrator.liangbin.bean.ShopClassDetailData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShopClassDetail;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * shop分类详情Activity
 */
public class ShopClassificationDetailActivity extends AppCompatActivity {

    private final String SHOP_CLASS_URL_LEFT = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=";
    private final String SHOP_CLASS_URL_RIGHT = "&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    private int page = 1;

    @BindView(R.id.shop_fr_detail_grid_view)
    PullToRefreshGridView gridView;

    private List<ShopClassDetailData> mList = new ArrayList<>();
    private ShopClassDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_classification_detail);
        ButterKnife.bind(this);

        initView();
    }

    private void initView(){
//        adapter = new ShopClassDetailAdapter(this,mList);
//        gridView.setAdapter(adapter);
        getIntentData();
        if (IsNetWorkUtils.geCurrentNetWorkIsConnected(this)){
            loadData();
        }else{
            Toast.makeText(this,"请检查网络连接",Toast.LENGTH_SHORT).show();
        }

        //设置可上拉和下拉
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置grid_view上拉下拉监听
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(ShopClassificationDetailActivity.this)){
                    loadData();
                    //刷新完成后退出刷新模式
                    gridView.onRefreshComplete();
                }else{
                    Toast.makeText(ShopClassificationDetailActivity.this,"请检查网络连接",Toast.LENGTH_SHORT).show();
                    //刷新完成后退出刷新模式
                    gridView.onRefreshComplete();
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                //刷新完成后退出刷新模式
                gridView.onRefreshComplete();
            }
        });

        //设置grid_view的item点击监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShopClassificationDetailActivity.this,ShopAddCarActivity.class);
                //传值
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",mList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getIntentData(){
        Intent intent = getIntent();
        page = intent.getIntExtra("page",0);
        Log.e("=======", "getIntentData: "+page);
    }

    /**
     * 加载网络数据,使用RxJava,在主线程运行
     */
    private void loadData(){
        HttpUtils.getRxJavaNetData(SHOP_CLASS_URL_LEFT +page+ SHOP_CLASS_URL_RIGHT,
                new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                if (str != null){
                    mList = ParseShopClassDetail.getData(str);
                    if (mList != null) {
//                        adapter.notifyDataSetChanged();
                        adapter = new ShopClassDetailAdapter(ShopClassificationDetailActivity.this,mList);
                        gridView.setAdapter(adapter);
                    }else{
                        Toast.makeText(ShopClassificationDetailActivity.this,"数据解析错误",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ShopClassificationDetailActivity.this,"网络请求错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
