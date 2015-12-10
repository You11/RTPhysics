package ru.b7.rtphysics.Calculator;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import ru.b7.rtphysics.R;

/**
 * Fragment we use in calc preference activity
 */
public class CalculatorPreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.calculator_preferences);
    }
}
