package me.pwcong.jpstart.conf;

import android.os.Environment;

import java.io.File;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class Constants {

    private Constants(){
        throw new RuntimeException("T_T");
    }

    public final static String PACKAGE_NAME="me.pwcong.jpstart";

    public static final String DB_NAME = "jpstart.db";

    public static final String DB_TABLE_NAME ="data";

    public static  final String DB_PATH = File.separator + "data"
            + Environment.getDataDirectory().getAbsolutePath() + File.separator
            + PACKAGE_NAME + File.separator + "databases" + File.separator;

    public static final String TYPE_YIN = "type_yin";
    public static final int TYPE_QINGYIN = 1;
    public static final int TYPE_ZHUOYIN = 2;
    public static final int TYPE_AOYIN = 3;

    public static final int ROW_QINGYIN = 11;
    public static final int COLUMN_QINGYIN = 5;
    public static final int ROW_ZHUOYIN = 5;
    public static final int COLUMN_ZHUOYIN = 5;
    public static final int ROW_AOYIN = 11;
    public static final int COLUMN_AOYIN = 3;

    public static final String TYPE_MING = "type_min";
    public static final int TYPE_HIRAGANA = 666;
    public static final int TYPE_KATAKANA = 999;

    public static final int TYPE_HEADER = 111;
    public static final int TYPE_ITEM = 222;
    public static final int TYPE_ITEM_DISABLE = 333;

    public static final String PREF_NAME = "pref_jpstart";

    public static final String CURRENT_ITEM = "current_item";

    public static final int ROW_FIRST = 1;





}
