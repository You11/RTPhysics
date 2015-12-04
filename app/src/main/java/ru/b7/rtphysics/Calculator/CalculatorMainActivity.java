package ru.b7.rtphysics.Calculator;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.EditText;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.R;

/**
 * Calculator Activity. Navigation drawer enabled, back button goes to HandbookMenuActivity.
 */
public class CalculatorMainActivity extends BaseActivity {

    public View.OnClickListener mListener = new View.OnClickListener() {

        CalculatorInputFormatter listener = new CalculatorInputFormatter();

        @Override
        public void onClick(View v) {
            CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);
            listener.doAction(v, editText);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_main);

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setBackgroundResource(android.R.color.transparent);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_CALCULATOR;
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
