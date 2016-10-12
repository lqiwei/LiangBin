package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.ShareData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 *  分享主界面gridView适配器
 */
public class ShareGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<ShareData> list = new ArrayList<>();

    public ShareGridViewAdapter(Context context, List<ShareData> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.share_gridview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.share_grid_view_img);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(context).load(list.get(position).getGoods_image()).into(viewHolder.imageView);

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
    }
}
