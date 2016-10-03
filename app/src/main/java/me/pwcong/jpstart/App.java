package me.pwcong.jpstart;

import android.app.Application;

import me.pwcong.jpstart.conf.Constants;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class App extends Application {

    private static App instance = null;

    public static boolean FLAG_MAIN = true;

    public static int CURRENT_ITEM = 0;
    public static int TYPE_MING = Constants.TYPE_HIRAGANA;

    public static int FROM_LAN = 0;
    public static int TO_LAN = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }

    public synchronized static App getInstance(){
        return instance;
    }
}
