package com.example.dicionario.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.dicionario.R;
import com.example.dicionario.view.LoginActivity;
import com.example.dicionario.view.Settings;

public class SettingsFragment extends PreferenceFragmentCompat {

    Context context;
    public SettingsFragment(Context context){
        this.context=context;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings,rootKey);
        Preference exit=(Preference)findPreference("exit");

        exit.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent= new Intent(context, LoginActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}
