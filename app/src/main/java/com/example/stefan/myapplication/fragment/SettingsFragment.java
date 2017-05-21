package com.example.stefan.myapplication.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.CheckBox;

import com.example.stefan.myapplication.R;

public class SettingsFragment extends PreferenceFragment  implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREF_SYNC = "pref_sync";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if (key.equals(KEY_PREF_SYNC)) {
            Preference connectionPref = findPreference(key);
            // Set summary to be the user-description for the selected value
            boolean settingsValue = sharedPreferences.getBoolean(key, false);
            ((CheckBox) getActivity().findViewById(R.id.checkBox_preferences)).setEnabled(settingsValue);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}