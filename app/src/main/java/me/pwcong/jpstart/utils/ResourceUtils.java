package me.pwcong.jpstart.utils;

import android.content.Context;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class ResourceUtils {

    private ResourceUtils(){
        throw new RuntimeException("O_O");
    }

    public static String getString(Context context,int resId){
        return context.getResources().getString(resId);
    }



}