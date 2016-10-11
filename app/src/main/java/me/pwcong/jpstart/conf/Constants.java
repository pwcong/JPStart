package me.pwcong.jpstart.conf;

import android.os.Environment;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class Constants {

    private Constants(){
        throw new RuntimeException("/(ㄒoㄒ)/~~");
    }

    public static final int OK                      = 200;

    public static final String CATEGORY_YIN         = "category_yin";
    public static final int CATEGORY_QINGYIN        = 1;
    public static final int CATEGORY_ZHUOYIN        = 2;
    public static final int CATEGORY_AOYIN          = 3;

    public static final int ROW_QINGYIN             = 12;
    public static final int COLUMN_QINGYIN          = 6;
    public static final int ROW_ZHUOYIN             = 6;
    public static final int COLUMN_ZHUOYIN          = 6;
    public static final int ROW_AOYIN               = 12;
    public static final int COLUMN_AOYIN            = 4;

    public static final int TYPE_HIRAGANA           = 666;
    public static final int TYPE_KATAKANA           = 999;

    public static final int TYPE_HEADER             = 0;
    public static final int TYPE_ITEM               = 1;
    public static final int TYPE_ITEM_DISABLE       = 2;

    public static final String PREF_NAME            = "pref_jpstart";

    public static final String FLAG_TIPS_JPSTART    = "flag_tips_jpstart";
    public static final String FLAG_TIPS_TRANSLATE  = "flag_tips_translate";
    public static final String FLAG_TIPS_MEMORY     = "flag_tips_memory";
    public static final String FLAG_TIPS_PIXIVILLUST  = "flag_tips_pixivillust";
    public static final String FLAG_TIPS_WIFI  = "flag_tips_wifi";

    public static final String MODE_ILLUST          = "mode_illust";
    public static final String IMG_URL              = "img_url";
    public static final String IMG_ID               = "img_id";

    public static final String FILEDIR_ROOT             = Environment.getExternalStorageDirectory()+"/JPStart";
    public static final String FILETYPE_JPG         = ".jpg";


    public static final String MODE_THEME = "mode_theme";
    public static final String MODE_AUTO = "auto";
    public static final String MODE_DAY = "day";
    public static final String MODE_NIGHT = "night";

    public static final String URL_PWCONG = "http://pwcong.me/";
    public static final String URL_PANDA = "http://weibo.com/1693463425/";

    public static final String IMG_BANNER = "img_banner";

    public static final String ALLOW_CONNECT_WITHOUT_WIFI = "allow_connect_without_wifi";






}
