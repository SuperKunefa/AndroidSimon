package com.example.simon;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class Preference extends PreferenceFragmentCompat {
    public int selected;
    SharedPreferences dataSp;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefs, rootKey);
        dataSp = getActivity().getSharedPreferences("level", 0);
        ListPreference gameDifficult = findPreference("LvlSel");
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        gameDifficult.setOnPreferenceChangeListener(new androidx.preference.Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(androidx.preference.Preference preference, Object newValue) {
                try
                {
                    selected = Integer.parseInt((String) newValue);
                }
                catch(NumberFormatException ex)
                {
                    ex.printStackTrace();
                }
                saveData(selected);
                return true;
            }
        });
    }

    public void saveData(int numOption)
    {
        SharedPreferences.Editor editor = dataSp.edit();
        editor.putInt("level", numOption);
        editor.commit();
    }
}