package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.ShopClassData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 * shop分类grid_view适配器
 */
public class ShopClassGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<ShopClassData> list = new ArrayList<>();

    public ShopClassGridViewAdapter(Context context, List<ShopClassData> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.shop_class_gridview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.shop_class_grid_view_iv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(context).load(list.get(position).getNew_cover_img()).into(viewHolder.imageView);

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
    }
}
