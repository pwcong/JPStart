package me.pwcong.jpstart;

import android.app.Application;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class App extends Application {

    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;

    }

    public synchronized static App getInstance(){
        return instance;
    }
}
