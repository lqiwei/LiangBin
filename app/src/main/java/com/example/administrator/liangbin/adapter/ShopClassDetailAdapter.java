package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.ShopClassDetailData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/11.
 * shop分类详情gridView的适配器
 */
public class ShopClassDetailAdapter extends BaseAdapter {

    private Context context;
    private List<ShopClassDetailData> list = new ArrayList<>();

    public ShopClassDetailAdapter(Context context, List<ShopClassDetailData> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.shop_class_detail_gridview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.shop_class_detail_grid_view_iv);
            viewHolder.goodsNameTv = (TextView) view.findViewById(R.id.shop_class_detail_details_tv);
            viewHolder.brandNameTv = (TextView) view.findViewById(R.id.shop_class_detail_name_tv);
            viewHolder.priceTv = (TextView) view.findViewById(R.id.shop_class_detail_money_tv);
            viewHolder.likeTv = (TextView) view.findViewById(R.id.shop_class_detail_love_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(list.get(position).getGoods_image()).into(viewHolder.imageView);
        viewHolder.goodsNameTv.setText(list.get(position).getGoods_name());
        viewHolder.brandNameTv.setText(list.get(position).getBrand_name());
        viewHolder.priceTv.setText(list.get(position).getPrice());
        viewHolder.likeTv.setText(list.get(position).getLike_count());

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView goodsNameTv;
        private TextView brandNameTv;
        private TextView priceTv;
        private TextView likeTv;
    }
}
