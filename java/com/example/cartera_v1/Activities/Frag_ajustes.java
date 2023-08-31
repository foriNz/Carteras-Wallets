package com.example.cartera_v1.Activities;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.cartera_v1.R;

public class Frag_ajustes extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}