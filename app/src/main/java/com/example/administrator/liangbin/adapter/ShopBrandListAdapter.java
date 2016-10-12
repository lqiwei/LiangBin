package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.ShopBrandData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 * shop品牌list_view适配器
 */
public class ShopBrandListAdapter extends BaseAdapter {

    private Context context;
    private List<ShopBrandData> list = new ArrayList<>();

    public ShopBrandListAdapter(Context context, List<ShopBrandData> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.shop_brand_listview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.shop_brand_list_view_left_iv);
            viewHolder.textView = (TextView) view.findViewById(R.id.shop_brand_list_view_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.textView.setText(list.get(position).getBrand_name());
        Picasso.with(context).load(list.get(position).getBrand_logo()).into(viewHolder.imageView);

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
