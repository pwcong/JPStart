package com.github.pwcong.jpstart.ui.fragment

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.ListPreference
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.PreferenceFragment
import android.util.Log

import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.constant.Constant
import com.github.pwcong.jpstart.manager.SharedPreferenceManager
import com.github.pwcong.jpstart.rxbus.RxBus
import com.github.pwcong.jpstart.rxbus.event.EventContainer
import com.github.pwcong.jpstart.rxbus.event.SettingEvent

class SettingFragment : PreferenceFragment() {
    private val TAG: String = javaClass.simpleName

    private lateinit var mThemesListPreference: ListPreference
    private lateinit var mConnectCheckBoxPreference: CheckBoxPreference

    private var modeTheme: String? = null
    private var allowConnect: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initVariable()
        initPreference()

        Log.i(TAG, "onCreate: OK")
    }

    private fun initView() {
        addPreferencesFromResource(R.xml.preference_setting)
    }

    private fun initVariable() {
        modeTheme = SharedPreferenceManager.getInstance()
            .getString(Constant.MODE_THEME, Constant.MODE_DAY)
        allowConnect = SharedPreferenceManager.getInstance()
            .getBoolean(Constant.ALLOW_CONNECT_WITHOUT_WIFI, false)
    }

    private fun initPreference() {
        mThemesListPreference = (preferenceManager.findPreference("setting_theme") as ListPreference?)!!
        mThemesListPreference.setEntries(R.array.themes_entries)
        mThemesListPreference.setEntryValues(R.array.themes_values)
        mThemesListPreference.value = modeTheme
        mThemesListPreference.onPreferenceChangeListener =
            OnPreferenceChangeListener { preference, newValue ->
                SharedPreferenceManager.getInstance()
                    .putString(Constant.MODE_THEME, newValue as String)
                RxBus.getDefault().post(
                    EventContainer(
                        EventContainer.TYPE_SETTING,
                        SettingEvent(R.string.reboot_to_take_effect)
                    )
                )
                true
            }

        mConnectCheckBoxPreference =
            (preferenceManager.findPreference("setting_wifi") as CheckBoxPreference?)!!
        mConnectCheckBoxPreference.isChecked = allowConnect
        mConnectCheckBoxPreference.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                Log.i(TAG, "onPreferenceChange: $newValue")
                SharedPreferenceManager.getInstance().putBoolean(
                    Constant.ALLOW_CONNECT_WITHOUT_WIFI,
                    (newValue as Boolean)
                )
                RxBus.getDefault().post(
                    EventContainer(
                        EventContainer.TYPE_SETTING,
                        SettingEvent(R.string.setting_effect)
                    )
                )
                true
            }
    }
}
