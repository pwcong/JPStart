package me.pwcong.jpstart.manager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPStartItem;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class DBManager {

    private final String TAG=getClass().getSimpleName();

    private static DBManager instance = null;

    private DBManager() {
    }

    public static synchronized DBManager getInstance(){

        if(instance==null){
            instance=new DBManager();
        }

        return instance;
    }

    public List<JPStartItem> query(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(Constants.DB_PATH + Constants.DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + Constants.DB_TABLE_NAME, null);
        List<JPStartItem> result = new ArrayList<>();
        JPStartItem item;
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int row = cursor.getInt(cursor.getColumnIndex("row"));
            int column = cursor.getInt(cursor.getColumnIndex("column"));
            String hiragana = cursor.getString(cursor.getColumnIndex("hiragana"));
            String katakana = cursor.getString(cursor.getColumnIndex("katakana"));
            String rome = cursor.getString(cursor.getColumnIndex("rome"));
            int category = cursor.getInt(cursor.getColumnIndex("category"));
            boolean existed = cursor.getInt(cursor.getColumnIndex("existed"))==1;

            item=new JPStartItem(id,row,column,hiragana,katakana,rome,category,existed);

            result.add(item);
        }
        cursor.close();
        db.close();
        return result;
    }


}
