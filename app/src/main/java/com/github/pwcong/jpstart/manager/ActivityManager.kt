package com.github.pwcong.jpstart.manager

import java.util.Vector
import java.util.concurrent.TimeUnit
import android.app.Activity
import android.util.Log
import rx.Observable
import rx.schedulers.Schedulers

class ActivityManager private constructor() {
    private val TAG: String = javaClass.simpleName

    private val activities = Vector<Activity>()
    private var current: Activity? = null

    fun getCurrent(): Activity? {
        return current
    }

    fun setCurrent(current: Activity?) {
        this.current = current
    }

    fun register(activity: Activity) {
        if (!activities.contains(activity)) {
            activities.add(activity)
            Log.i(TAG, "register: OK -> $activity")
        }
    }

    fun unregister(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
            Log.i(TAG, "unregister: OK -> $activity")
            Log.i(TAG, "unregister: ALL -> $activities")
        }
    }

    fun finishAll() {
        current = null

        for (t in activities) {
            t.finish()
            Log.i(TAG, "unregister: OK -> $t")
        }
        activities.clear()
        Log.i(TAG, "unregister: ALL -> $activities")

        // 三秒后完全退出程序，清空后台
        Observable.timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                System.exit(0)
            }
    }

    companion object {
        private var instance: ActivityManager? = null

        @Synchronized
        fun getInstance(): ActivityManager {
            if (instance == null) {
                instance = ActivityManager()
            }

            return instance!!
        }
    }
}

