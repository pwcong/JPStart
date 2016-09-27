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

    public static final String TYPE="type";

    public static final int TYPE_HIRAGANA = 666;
    public static final int TYPE_KATAKANA = 999;



}
