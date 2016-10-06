package me.pwcong.jpstart;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;

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
        setDayNightMode(SharedPreferenceManager.getInstance().getInt(Constants.MODE,Constants.MODE_AUTO));
    }

    public synchronized static App getInstance(){
        return instance;
    }



    private void setDayNightMode(int mode){

        switch (mode){
            case Constants.MODE_AUTO:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
            case Constants.MODE_NIGHT:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Constants.MODE_DAY:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;

        }




    }


}
