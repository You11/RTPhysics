package ru.b7.rtphysics.Calculator;

import android.os.Bundle;
import android.view.View;

import ru.b7.rtphysics.BaseActivity;

/**
 * Preferences menu of calc
 */
public class CalculatorPreferences extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            CalculatorPreferencesFragment fragment = new CalculatorPreferencesFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
