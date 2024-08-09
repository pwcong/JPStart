package me.pwcong.jpstart.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Pwcong on 2016/9/28.
 */

public class JPStartDatabase extends SQLiteAssetHelper {

    public static final String DB_NAME = "jpstart.db";
    public static final String DB_TABLE_NAME = "data";
    public static final int DB_VERSON = 1;

    public JPStartDatabase(Context context) {

        super(context, DB_NAME, null, DB_VERSON);

    }

}
