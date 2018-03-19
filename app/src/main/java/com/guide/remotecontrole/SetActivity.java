package com.guide.remotecontrole;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SetActivity extends PreferenceActivity {
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setTheme(getUserTheme());
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
    private int getUserTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SetActivity.this);
        String userTheme = preferences.getString("pref_theme","light");
        if (userTheme.equals("light")) {
            return R.style.AppTheme;
        } else {
            return R.style.AppTheme_Dark;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SetActivity.this);

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if (s.equals("pref_theme")) {
                    recreate();
                    updateThemeSummery();
                } else if (s.equals("pref_ip")) {
                    updateAdressSummery();
                }
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(listener);

        updateAdressSummery();
        updateThemeSummery();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SetActivity.this);
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
    private void updateAdressSummery() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString("pref_ip", "DEFAULT");

        Preference preference = findPreference("pref_ip");
        preference.setSummary(value);
    }
    private void updateThemeSummery() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString("pref_theme", "DEFAULT");

        Preference preference = findPreference("pref_theme");
        preference.setSummary(value);
    }
 }
