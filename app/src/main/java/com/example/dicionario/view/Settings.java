package com.example.dicionario.view;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.dicionario.R;
import com.example.dicionario.fragments.SettingsFragment;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().replace(R.id.settingsView,new SettingsFragment(Settings.this)).commit();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}