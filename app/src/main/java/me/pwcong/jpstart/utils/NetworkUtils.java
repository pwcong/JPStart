package me.pwcong.jpstart.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Pwcong on 2016/10/11.
 */

public class NetworkUtils {

    private NetworkUtils(){
        throw new RuntimeException("(￣▽￣)");
    }

    public static boolean checkWifi(Context context){

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        return networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI;

    }

}
