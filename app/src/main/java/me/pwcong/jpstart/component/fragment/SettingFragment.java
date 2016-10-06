package me.pwcong.jpstart.component.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.conf.Constants;
import me.pwcong.jpstart.manager.SharedPreferenceManager;
import me.pwcong.jpstart.rxbus.RxBus;
import me.pwcong.jpstart.rxbus.event.EventContainer;
import me.pwcong.jpstart.rxbus.event.SettingEvent;

/**
 * Created by Pwcong on 2016/10/6.
 */

public class SettingFragment extends PreferenceFragment {

    private final String TAG=getClass().getSimpleName();

    ListPreference mThemesListPreference;

    String mode_theme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initVariable();
        initPreference();

        Log.i(TAG, "onCreate: OK");

    }

    private void initView(){
        addPreferencesFromResource(R.xml.preference_setting);
    }


    private void initVariable(){

        mode_theme = SharedPreferenceManager.getInstance().getString(Constants.MODE_THEME,Constants.MODE_AUTO);

    }


    private void initPreference(){
        mThemesListPreference = (ListPreference) getPreferenceManager().findPreference("setting_theme");
        mThemesListPreference.setEntries(R.array.themes_entries);
        mThemesListPreference.setEntryValues(R.array.themes_values);
        mThemesListPreference.setValue(mode_theme);
        mThemesListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                Log.i(TAG, "onPreferenceChange: "+newValue);
                SharedPreferenceManager.getInstance().putString(Constants.MODE_THEME, (String) newValue);
                RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_SETTING,new SettingEvent(R.string.reboot_to_take_effect)));

                return true;
            }
        });


    }

}
