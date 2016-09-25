package me.pwcong.jpstart;

import android.app.Application;

import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.utils.DBUtils;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class App extends Application {

    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;

        DBUtils.copyFile(this, Constants.DB_PATH,Constants.DB_NAME);

    }

    public synchronized static App getInstance(){
        return instance;
    }
}
