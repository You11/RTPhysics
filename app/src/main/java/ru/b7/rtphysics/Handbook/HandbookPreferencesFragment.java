package ru.b7.rtphysics.Handbook;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import ru.b7.rtphysics.R;

/**
 * fragment for handbook prefs
 */
public class HandbookPreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.handbook_preferences);
    }
}
