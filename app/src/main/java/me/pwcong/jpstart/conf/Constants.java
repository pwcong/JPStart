package me.pwcong.jpstart.conf;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class Constants {

    private Constants(){
        throw new RuntimeException("/(ㄒoㄒ)/~~");
    }

    public static final int OK = 200;

    public static final String CATEGORY_YIN = "category_yin";
    public static final int CATEGORY_QINGYIN = 1;
    public static final int CATEGORY_ZHUOYIN = 2;
    public static final int CATEGORY_AOYIN = 3;

    public static final int ROW_QINGYIN = 12;
    public static final int COLUMN_QINGYIN = 6;
    public static final int ROW_ZHUOYIN = 6;
    public static final int COLUMN_ZHUOYIN = 6;
    public static final int ROW_AOYIN = 12;
    public static final int COLUMN_AOYIN = 4;

    public static final int TYPE_HIRAGANA = 666;
    public static final int TYPE_KATAKANA = 999;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_ITEM_DISABLE = 2;

    public static final String PREF_NAME = "pref_jpstart";

    public static final String FLAG_TIPS_JPSTART = "flag_tips_jpstart";
    public static final String FLAG_TIPS_TRANSLATE = "flag_tips_translate";









}
