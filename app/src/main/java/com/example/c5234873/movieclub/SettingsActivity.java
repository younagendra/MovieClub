package com.example.c5234873.movieclub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.prefs.PreferenceChangeListener;

import static android.R.id.list;

/**
 * Created by C5234873 on 11/4/2016.
 */

public class SettingsActivity extends AppCompatActivity {

    public SettingsActivity() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public static class PreferenceFragement extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        public PreferenceFragement() {

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_items);

            Preference pop_pref = findPreference(getString(R.string.preference_value));
            bindPreferenceSummary(pop_pref);
        }

        private void bindPreferenceSummary(Preference pop_pref) {
            pop_pref.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(pop_pref.getContext());
            String filter_value = sharedPref.getString(pop_pref.getKey(), "");
            onPreferenceChange(pop_pref, filter_value);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String movie_by_value = newValue.toString();
            if (preference instanceof ListPreference) {
                int id = ((ListPreference) preference).findIndexOfValue(movie_by_value);
                if (id >= 0) {
                    CharSequence[] lables = ((ListPreference) preference).getEntries();
                    preference.setSummary(lables[id]);
                }

            } else {
                preference.setSummary(newValue.toString());
            }
            return true;
        }
    }
}
