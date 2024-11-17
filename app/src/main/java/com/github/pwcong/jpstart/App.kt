package com.github.pwcong.jpstart

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.blankj.utilcode.util.NetworkUtils

import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.manager.SharedPreferenceManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        setDayNightMode(
            SharedPreferenceManager.getInstance().getString(
                Constants.MODE_THEME,
                Constants.MODE_DAY
            )
        )

        ISWIFI = NetworkUtils.isWifiConnected()
    }

    private fun setDayNightMode(mode: String?) {
        when (mode) {
            Constants.MODE_AUTO -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )

            Constants.MODE_NIGHT -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )

            Constants.MODE_DAY -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )

            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    companion object {
        private lateinit var instance: App

        var CURRENT_ITEM: Int = 0
        var TYPE_MING: Int = Constants.TYPE_HIRAGANA

        var FROM_LAN: Int = 0
        var TO_LAN: Int = 0

        var ISWIFI: Boolean = false

        @Synchronized
        fun getInstance(): App {
            return instance
        }
    }
}