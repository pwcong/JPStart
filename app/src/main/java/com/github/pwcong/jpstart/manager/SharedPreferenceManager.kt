package com.github.pwcong.jpstart.manager

import android.content.Context
import android.content.SharedPreferences

import com.github.pwcong.jpstart.App
import com.github.pwcong.jpstart.constant.Constant

class SharedPreferenceManager private constructor() {
    private val sharedPreferences: SharedPreferences = App.getInstance()
        .getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)

    fun getString(key: String?, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    fun putString(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getInt(key: String?, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun putInt(key: String?, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun putBoolean(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    companion object {
        private var instance: SharedPreferenceManager? = null

        @Synchronized
        fun getInstance(): SharedPreferenceManager {
            if (instance == null) {
                instance = SharedPreferenceManager()
            }

            return instance!!
        }
    }
}