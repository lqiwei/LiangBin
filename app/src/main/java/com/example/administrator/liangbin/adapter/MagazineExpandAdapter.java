package com.example.administrator.liangbin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.liangbin.R;
import com.example.administrator.liangbin.bean.MagazineChildData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/8.
 * 杂志主界面ExpandableListView适配器
 */
public class MagazineExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> stringList = new ArrayList<>();
    private Map<String,List<MagazineChildData>> map = new HashMap<>();

    public MagazineExpandAdapter(Context context, List<String> stringList, Map<String, List<MagazineChildData>> map) {
        this.context = context;
        this.stringList = stringList;
        this.map = map;
        Log.e("ggggggggggggg", "MagazineExpandAdapter: "+this.stringList);
    }

    @Override
    public int getGroupCount() {
        return stringList == null ? 0 : stringList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<MagazineChildData> childDatas = map.get(stringList.get(groupPosition));
        return childDatas == null ? 0 : childDatas.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        if (groupPosition == 0){
            textView.setHeight(0);
        }else{
            textView.setBackgroundColor(Color.parseColor("#222222"));
            textView.setText("- "+stringList.get(groupPosition)+" -");
            textView.setTextColor(Color.parseColor("#007777"));
            textView.setHeight(50);
            textView.setGravity(Gravity.CENTER);
        }

        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.magazine_listview_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.magazine_expand_list_view_item_img);
            viewHolder.detailTv = (TextView) view.findViewById(R.id.magazine_expand_list_view_item_detail_tv);
            viewHolder.typeTv = (TextView) view.findViewById(R.id.magazine_expand_list_view_item_type_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        MagazineChildData childData = map.get(stringList.get(groupPosition)).get(childPosition);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.detailTv.setText(childData.getTopic_name());
        viewHolder.typeTv.setText(childData.getCat_name());
        Picasso.with(context).load(childData.getCover_img_new()).into(viewHolder.imageView);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView detailTv;
        private TextView typeTv;
    }
}
