package com.example.administrator.liangbin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.administrator.liangbin.bean.ShopClassDetailData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 * 数据库管理类
 */
public class DBManager {

    private SQLiteDatabase mDb;
    private static DBManager manager = null;

    /**
     * 获取数据库管理对象
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context){
        if (manager == null){
            manager = new DBManager(context);
        }
        return manager;
    }

    /**
     * 创建数据库
     */
    public DBManager(Context context){
        mDb = context.openOrCreateDatabase(DBInfo.DB_NAME,Context.MODE_PRIVATE,null);
        String sql = "create table if not exists "+ DBInfo.TABLE_NAME +
                "("+DBInfo._ID +" integer primary key autoincrement ,"
                +DBInfo.PIC_URL+" text, "+DBInfo.GOODS_NAME+" text, "+DBInfo.PRICE+" text"+")";
        mDb.execSQL(sql);
    }

    /**
     * 插入数据
     */
    public void insertData(Context context,ShopClassDetailData data){
        ContentValues cv = new ContentValues();
        cv.put(DBInfo.PIC_URL,data.getGoods_image());
        cv.put(DBInfo.GOODS_NAME,data.getGoods_name());
        cv.put(DBInfo.PRICE,data.getPrice());
        if (mDb != null) {
            long rowId = mDb.insert(DBInfo.TABLE_NAME, null, cv);
            if (rowId < 0) {
                Toast.makeText(context, "加入失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "加入成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 查询数据
     */
    public List<ShopClassDetailData> queryData(){
        List<ShopClassDetailData> list = new ArrayList<>();
        if (mDb != null){
            Cursor cursor = mDb.query(DBInfo.TABLE_NAME, null, null, null, null, null, null, null);
            while (cursor.moveToNext()){
                ShopClassDetailData detailData = new ShopClassDetailData();
                int id = cursor.getInt(cursor.getColumnIndex(DBInfo._ID));
                String url = cursor.getString(cursor.getColumnIndex(DBInfo.PIC_URL));
                String name = cursor.getString(cursor.getColumnIndex(DBInfo.GOODS_NAME));
                String price = cursor.getString(cursor.getColumnIndex(DBInfo.PRICE));
                detailData.goods_image = url;
                detailData.goods_name = name;
                detailData.price = price;
                list.add(detailData);
            }
        }
        return list;
    }
}
