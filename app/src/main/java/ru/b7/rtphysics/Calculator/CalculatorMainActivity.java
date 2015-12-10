package ru.b7.rtphysics.Calculator;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.R;

/**
 * Calculator Activity. Navigation drawer enabled, back button goes to HandbookMenuActivity.
 */
public class CalculatorMainActivity extends BaseActivity {

    public View.OnClickListener mListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CalculatorPadButton calcButton = (CalculatorPadButton) v;
            CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);

            switch (calcButton.getId()) {
                case R.id.calc_button_clr:
                    editText.clearInput();
                    break;
                case R.id.calc_button_del:
                    editText.deleteLastSymbol();
                    break;
                case R.id.calc_button_func:
                    Spinner spinner = spinnerForButton(R.array.functions_array, v);
                    spinner.performClick();
                    break;
                case R.id.calc_button_func_last:
                    break;
                case R.id.calc_button_symb:
                    spinner = spinnerForButton(R.array.symbols_array, v);
                    spinner.performClick();
                    break;
                case R.id.calc_button_symb_last:
                    break;
                case R.id.calc_button_enter:
                    CalculatorCalculatingRPN calculator = new CalculatorCalculatingRPN(CalculatorMainActivity.this);
                    String result = calculator.calculate(calculator.doTransitionInRPN(editText.getText().toString()));
                    editText.setText(roundAnswer(result));
                    break;
                default:
                    editText.insert(calcButton.getText().toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_main);

        CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);
        editText.setBackgroundResource(android.R.color.transparent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, CalculatorPreferences.class));
        }

        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_CALCULATOR;
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

    private Spinner spinnerForButton(int stringResource, View v) {
        //программный спиннер отказывался работать без активити, поэтому пускай будет здесь
        Spinner spinner = (Spinner) findViewById(R.id.calculator_button_spinner);

        int[] location = new int[2];
        v.getLocationOnScreen(location);

        spinner.setPadding(location[0] + v.getWidth() * 3 / 4, 0, 0, 0);
        spinner.setDropDownVerticalOffset(location[1]);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                stringResource,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean selection = false;
            CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (selection) {
                    editText.insert(parent.getSelectedItem().toString());
                } else {
                    selection = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return spinner;
    }

    private String roundAnswer(String input) {
        int roundValue = PreferenceManager.getDefaultSharedPreferences(this).
                getInt("NUMBER_OF_DIGITS_IN_ANSWER",
                        this.getResources().
                                getInteger(R.integer.calc_default_number_of_digits_in_answer));
        BigDecimal bd = new BigDecimal(input);
        if (bd.scale() > roundValue) {
            bd = bd.setScale(roundValue, RoundingMode.HALF_UP);
        }
        return bd.toString();
    }
}