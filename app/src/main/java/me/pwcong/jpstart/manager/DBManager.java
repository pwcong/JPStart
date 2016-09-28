package me.pwcong.jpstart.manager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPItem;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class DBManager {

    private static DBManager instance = null;

    private static List<JPItem> query = null;
    private static List<JPItem> qingYin = null;
    private static List<JPItem> zhuoYin = null;
    private static List<JPItem> aoYin = null;

    private DBManager() {
    }

    public static synchronized DBManager getInstance(){

        if(instance==null){
            instance=new DBManager();
        }

        return instance;
    }

    public synchronized List<JPItem> query(){

        if(query==null){
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(Constants.DB_PATH + Constants.DB_NAME, null);
            Cursor cursor = db.rawQuery("select * from " + Constants.DB_TABLE_NAME, null);
            query = new ArrayList<>();
            JPItem item;
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int row = cursor.getInt(cursor.getColumnIndex("row"));
                int column = cursor.getInt(cursor.getColumnIndex("column"));
                String hiragana = cursor.getString(cursor.getColumnIndex("hiragana"));
                String katakana = cursor.getString(cursor.getColumnIndex("katakana"));
                String rome = cursor.getString(cursor.getColumnIndex("rome"));
                int category = cursor.getInt(cursor.getColumnIndex("category"));
                boolean existed = cursor.getInt(cursor.getColumnIndex("existed"))==1;

                item=new JPItem(id,row,column,hiragana,katakana,rome,category,existed);

                query.add(item);
            }
            cursor.close();
            db.close();
        }

        return query;
    }

    public synchronized List<JPItem> getYin(int type_yin){

        switch (type_yin){

            case Constants.TYPE_QINGYIN:return getQingYin();
            case Constants.TYPE_ZHUOYIN:return getZhuoYin();
            case Constants.TYPE_AOYIN:return getAoYin();
            default:return null;
        }

    }



    public synchronized List<JPItem> getQingYin(){

        if(qingYin==null){
            List<JPItem> query = query();
            qingYin=new ArrayList<>();
            for(JPItem item:query){

                if(item.getCategory()==Constants.TYPE_QINGYIN){
                    qingYin.add(item);
                }

            }

        }

        return qingYin;
    }
    public synchronized List<JPItem> getZhuoYin(){

        if(zhuoYin==null){
            List<JPItem> query = query();
            zhuoYin=new ArrayList<>();
            for(JPItem item:query){

                if(item.getCategory()==Constants.TYPE_ZHUOYIN){
                    zhuoYin.add(item);
                }

            }

        }

        return zhuoYin;
    }

    public synchronized List<JPItem> getAoYin(){

        if(aoYin==null){
            List<JPItem> query = query();
            aoYin=new ArrayList<>();
            for(JPItem item:query){

                if(item.getCategory()==Constants.TYPE_AOYIN){
                    aoYin.add(item);
                }

            }

        }
        return aoYin;
    }




}
