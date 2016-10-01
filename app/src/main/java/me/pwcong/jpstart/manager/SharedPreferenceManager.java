package me.pwcong.jpstart.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import me.pwcong.jpstart.App;
import me.pwcong.jpstart.conf.Constants;

/**
 * Created by Pwcong on 2016/9/27.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance = null;

    private final SharedPreferences sharedPreferences;

    private SharedPreferenceManager(){

        sharedPreferences = App.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);

    }

    public static synchronized SharedPreferenceManager getInstance(){

        if(instance==null){
            instance=new SharedPreferenceManager();
        }

        return instance;
    }

    public String getString(String key, @Nullable String defValue){

        return sharedPreferences.getString(key,defValue);

    }

    public void putString(String key,String value){

        sharedPreferences.edit().putString(key,value).apply();

    }

    public int getInt(String key,int defValue){

        return sharedPreferences.getInt(key,defValue);

    }

    public void putInt(String key,int value){
        sharedPreferences.edit().putInt(key,value).apply();

    }

    public boolean getBoolean(String key,boolean defValue){
        return sharedPreferences.getBoolean(key,defValue);
    }

    public void putBoolean(String key,boolean value){
        sharedPreferences.edit().putBoolean(key,value).apply();
    }

}
