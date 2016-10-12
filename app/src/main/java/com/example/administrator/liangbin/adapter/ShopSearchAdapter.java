package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.ShopSearchData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 * 商品搜索gridView适配器
 */
public class ShopSearchAdapter extends BaseAdapter {

    private Context context;
    private List<ShopSearchData> list = new ArrayList<>();

    public ShopSearchAdapter(Context context, List<ShopSearchData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.shop_search_gridview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.shop_search_item_img);
            viewHolder.contentTv = (TextView) view.findViewById(R.id.shop_search_content_tv);
            viewHolder.priceTv = (TextView) view.findViewById(R.id.shop_search_price_tv);
            viewHolder.likeTv = (TextView) view.findViewById(R.id.shop_search_like_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(context).load(list.get(position).getGoods_image()).into(viewHolder.imageView);
        viewHolder.contentTv.setText(list.get(position).getGoods_name());
        viewHolder.priceTv.setText(list.get(position).getPrice());
        viewHolder.likeTv.setText(list.get(position).getLike_count());

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView contentTv;
        private TextView priceTv;
        private TextView likeTv;
    }
}
