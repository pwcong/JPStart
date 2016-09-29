package me.pwcong.jpstart.manager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.R;
import me.pwcong.jpstart.comparator.JPItemComporator;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.db.JPStartDatabase;
import me.pwcong.jpstart.mvp.bean.JPItem;
import me.pwcong.jpstart.utils.ResourceUtils;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class DBManager {

    private static DBManager instance = null;

    private List<JPItem> query = null;
    private List<JPItem> qingYin = null;
    private List<JPItem> zhuoYin = null;
    private List<JPItem> aoYin = null;

    private DBManager() {
    }

    public static synchronized DBManager getInstance(){

        if(instance==null){
            instance=new DBManager();
        }

        return instance;
    }

    public void init(){

        getQingYin();
        getZhuoYin();
        getAoYin();

    }


    public synchronized List<JPItem> query(){

        if(query==null){
            SQLiteDatabase db = new JPStartDatabase(App.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPStartDatabase.DB_TABLE_NAME, null);
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
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                boolean existed = cursor.getInt(cursor.getColumnIndex("existed"))==1;

                item=new JPItem(id,row,column,hiragana,katakana,rome,category,type,existed);

                query.add(item);
            }
            cursor.close();
            db.close();
        }

        return query;
    }

    public synchronized List<JPItem> getQingYin() {

        if (qingYin==null){

            qingYin=new ArrayList<>();
            List<JPItem> query = query();
            for(JPItem item:query){

                if(item.getCategory()==Constants.CATEGORY_QINGYIN){
                    qingYin.add(item);
                }

            }

            Collections.sort(qingYin,new JPItemComporator());

            addHeaderString(qingYin,Constants.ROW_QINGYIN,Constants.COLUMN_QINGYIN);


        }

        return qingYin;
    }

    public List<JPItem> getZhuoYin() {

        if (zhuoYin==null){

            zhuoYin=new ArrayList<>();
            List<JPItem> query = query();
            for(JPItem item:query){

                if(item.getCategory()==Constants.CATEGORY_ZHUOYIN){
                    zhuoYin.add(item);
                }

            }

            Collections.sort(zhuoYin,new JPItemComporator());

            addHeaderString(zhuoYin,Constants.ROW_ZHUOYIN,Constants.COLUMN_ZHUOYIN);


        }


        return zhuoYin;
    }

    public List<JPItem> getAoYin() {

        if (aoYin==null){

            aoYin=new ArrayList<>();
            List<JPItem> query = query();
            for(JPItem item:query){

                if(item.getCategory()==Constants.CATEGORY_AOYIN){
                    aoYin.add(item);
                }

            }

            Collections.sort(aoYin,new JPItemComporator());

            addHeaderString(aoYin,Constants.ROW_AOYIN,Constants.COLUMN_AOYIN);

        }



        return aoYin;
    }

    public static void addHeaderString(List<JPItem> list,int row,int column){

        for(int i=1;i<column;i++){

            JPItem item = list.get(i);
            String hiragana = item.getHiragana();
            String katakana = item.getKatakana();
            item.setHiragana(hiragana+ResourceUtils.getString(App.getInstance(),R.string.column));
            item.setKatakana(katakana+ResourceUtils.getString(App.getInstance(),R.string.column));

        }

        for(int i = 1;i<row;i++){

            JPItem item = list.get(i * column);
            String hiragana = item.getHiragana();
            String katakana = item.getKatakana();
            item.setHiragana(hiragana+ResourceUtils.getString(App.getInstance(),R.string.row));
            item.setKatakana(katakana+ResourceUtils.getString(App.getInstance(),R.string.row));


        }



    }

}
