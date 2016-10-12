package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 * 购物车list_view适配器
 */
public class ShopCarListViewAdapter extends BaseAdapter {

    //是否全部选中
    private boolean isSelectBoth = false;
    //是否点击选中按钮
    private boolean isSelect = false;
    private Map<Integer,Boolean> map = new HashMap<>();
    //总金额
    private int price;

    private List<ShopClassDetailData> list = new ArrayList<>();
    private Context context;

    public ShopCarListViewAdapter(List<ShopClassDetailData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * 是否全选
     * @param isSelectBoth
     */
    public void setSelectBoth(boolean isSelectBoth){
        this.isSelectBoth = isSelectBoth;
        map.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ShopClassDetailData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View view = null;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.shop_car_list_view_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.shop_car_item_img);
            viewHolder.topTv = (TextView) view.findViewById(R.id.shop_car_item_text);
            viewHolder.bottomTv = (TextView) view.findViewById(R.id.shop_car_item_price);
            viewHolder.selectImg = (ImageView) view.findViewById(R.id.select_shop_car_item_img);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(context).load(list.get(position).getGoods_image()).into(viewHolder.imageView);
        viewHolder.topTv.setText(list.get(position).getGoods_name());
        viewHolder.bottomTv.setText(list.get(position).getPrice());

        isBuy(viewHolder,position);

        return view;
    }

    /**
     * 商品是否选中
     */
    private void isBuy(final ViewHolder viewHolder,final int position){
        //是否全选
        if (isSelectBoth){
            viewHolder.selectImg.setImageResource(R.drawable.ic_cart_selected);
        }else{
            viewHolder.selectImg.setImageResource(R.drawable.ic_cart_unselected);
        }
        //监听图片的点击
        viewHolder.selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次点击保存状态
                if (!isSelect){
                    isSelect = true;
                    map.put(position,true);
                    viewHolder.selectImg.setImageResource(R.drawable.ic_cart_selected);
//                    notifyDataSetChanged();
                    price += Float.parseFloat(list.get(position).getPrice());
                }else{
                    isSelect = false;
                    map.put(position,false);
                    viewHolder.selectImg.setImageResource(R.drawable.ic_cart_unselected);
//                    notifyDataSetChanged();
                    price -= Float.parseFloat(list.get(position).getPrice());
                }
                Log.e("=====price====", "isBuy: "+price);
            }
        });
        //展示列表取出状态
        if (map.get(position) != null) {
            if (map.get(position)) {
                Log.e("=========", "isBuy: "+map.get(position));
                viewHolder.selectImg.setImageResource(R.drawable.ic_cart_selected);
                notifyDataSetChanged();
            } else {
                Log.e("=========", "isBuy: "+map.get(position));
                viewHolder.selectImg.setImageResource(R.drawable.ic_cart_unselected);
                notifyDataSetChanged();
            }
        }
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView topTv;
        private TextView bottomTv;
        private ImageView selectImg;
    }
}
