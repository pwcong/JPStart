package me.pwcong.jpstart.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.mvp.bean.JPStartItem;

/**
 * Created by Pwcong on 2016/9/25.
 */

public class DBManager {

    private final String TAG=getClass().getSimpleName();

    private static final String DB_NAME = "jpstart.db";

    private static final String DB_TABLE_NAME ="data";

    private static final int BUFFER_SIZE = 1024;

    private static  String DB_PATH;

    private static DBManager instance = null;

    private DBManager() {
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + Constants.PACKAGENAME + File.separator + "databases" + File.separator;
        Log.i(TAG, "DBManager: "+DB_PATH);

    }

    public static synchronized DBManager getInstance(){

        if(instance==null){
            instance=new DBManager();
        }

        return instance;

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void initDBFile(Context context){
        
        File dir = new File(DB_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()){
            InputStream is;
            OutputStream os;
            try {
                is = context.getResources().getAssets().open(DB_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0){
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.i(TAG, "initDBFile: OK");
    }

    public List<JPStartItem> query(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + DB_TABLE_NAME, null);
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
