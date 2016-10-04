package me.pwcong.jpstart.manager;

import android.app.Activity;
import android.util.Log;

import java.util.Vector;

/**
 * Created by Pwcong on 2016/9/24.
 */

public class ActivityManager {

    private final String TAG=getClass().getSimpleName();

    private static ActivityManager instance = null;

    private Vector<Activity> activities = new Vector<>();

    public static Activity current;

    private ActivityManager() {
    }


    public synchronized static ActivityManager getInstance(){

        if(instance==null){
            instance=new ActivityManager();
        }

        return instance;
    }

    public void register(Activity activity){

        if(!activities.contains(activity)){
            activities.add(activity);
            Log.i(TAG, "register: OK -> " + activity);
            
        }

    }

    public void unregister(Activity activity){

        if(activities.contains(activity)){
            activities.remove(activity);
            Log.i(TAG, "unregister: OK -> "+ activity);
            Log.i(TAG, "unregister: ALL -> "+activities);
        }

    }


    public void finishAll(){

        current=null;

        for(Activity t:activities){
            t.finish();
            Log.i(TAG, "unregister: OK -> "+ t);
        }
        activities.clear();
        Log.i(TAG, "unregister: ALL -> "+activities);
    }

    public static Activity getCurrent() {
        return current;
    }

    public static void setCurrent(Activity current) {
        ActivityManager.current = current;
    }
}
