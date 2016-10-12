package com.example.administrator.liangbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.liangbin.adapter.ShopSearchAdapter;
import com.example.administrator.liangbin.bean.ShopSearchData;
import com.example.administrator.liangbin.callback.OnJsonLisiter;
import com.example.administrator.liangbin.http.HttpUtils;
import com.example.administrator.liangbin.http.IsNetWorkUtils;
import com.example.administrator.liangbin.parse.ParseShopSearch;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    private final String SEARCH_GOODS_URL_LEFT = "http://mobile.iliangcang.com/goods/search?app_key=Android&count=10&is_outter=0&keyword=";
    private final String SEARCH_GOODS_URL_RIGHT = "&page=1&sig=6D569443F5A6EB51036D09737946AC2A%7C002841520425331&v=1.0";
    private List<ShopSearchData> mList = new ArrayList<>();
    private ShopSearchAdapter adapter;

    @BindView(R.id.search_goods_left_iv)
    ImageView backImg;
    @BindView(R.id.search_goods_auto_tv)
    EditText inputEt;
    @BindView(R.id.search_goods_search_iv)
    ImageView searchImg;
    @BindView(R.id.search_goods_grid_view)
    PullToRefreshGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        ButterKnife.bind(this);

        initView();
    }

    /**
     * 初始化视图
     */
    private void initView(){

        //设置返回上层界面监听
        backImg.setOnClickListener(this);
        //设置搜索监听
        searchImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回上层界面
            case R.id.search_goods_left_iv:
                finish();
                break;
            //执行搜索加载数据
            case R.id.search_goods_search_iv:
                if (IsNetWorkUtils.geCurrentNetWorkIsConnected(this)){
                    loadData();
                }else{
                    Toast.makeText(this,"请检查网络连接",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 加载网络数据
     */
    private void loadData(){
        HttpUtils.getRxJavaNetData(SEARCH_GOODS_URL_LEFT + inputEt.getText().toString() +
                SEARCH_GOODS_URL_RIGHT, new OnJsonLisiter() {
            @Override
            public void setOnJsonLisiter(String str) {
                if (str != null){
                    Toast.makeText(SearchGoodsActivity.this,"正在加载中......",Toast.LENGTH_SHORT).show();
                    mList = ParseShopSearch.getData(str);
                    adapter = new ShopSearchAdapter(SearchGoodsActivity.this,mList);
                    gridView.setAdapter(adapter);
                }else{
                    Toast.makeText(SearchGoodsActivity.this,"无相关产品",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
