package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.MasterData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 * 达人grid_view适配器
 */
public class MasterGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<MasterData> list = new ArrayList<>();

    public MasterGridViewAdapter(Context context, List<MasterData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.master_gridview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.master_grid_view_item_img);
            viewHolder.nameTv = (TextView) view.findViewById(R.id.master_grid_view_item_name_tv);
            viewHolder.typeTv = (TextView) view.findViewById(R.id.master_grid_view_item_type_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.nameTv.setText(list.get(position).getUsername());
        viewHolder.typeTv.setText(list.get(position).getDuty());
        Picasso.with(context).load(list.get(position).getOrig()).into(viewHolder.imageView);

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView nameTv;
        private TextView typeTv;
    }
}
