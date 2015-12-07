package ru.b7.rtphysics.Calculator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import ru.b7.rtphysics.R;

/**
 * Listener to buttons on calc, does some shit when they are pressed
 */
public class CalculatorInputFormatter {

    private Context context;

    public CalculatorInputFormatter(Context context) {
        this.context = context;
    }

    public void doAction(View v, CalculatorEditText editText) {

        CalculatorPadButton calcButton = (CalculatorPadButton) v;

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
                editText.insert(spinner.getSelectedItem().toString());
                break;
            case R.id.calc_button_func_last:
                break;
            case R.id.calc_button_symb:
                spinner = spinnerForButton(R.array.symbols_array, v);

                spinner.getSelectedItem();
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, "Position = ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(context, "Position = ", Toast.LENGTH_SHORT).show();
                    }
                });

                spinner.performClick();
                break;
            case R.id.calc_button_symb_last:
                break;
            case R.id.calc_button_enter:
                CalculatorCalculatingRPN calculator = new CalculatorCalculatingRPN();
                String result = calculator.calculate(calculator.doTransitionInRPN(editText.getText().toString()));
                editText.setText(result);
                break;
            default:
                editText.insert(calcButton.getText().toString());
        }
    }

    private Spinner spinnerForButton(int stringResource, View v) {

        Spinner spinner = new Spinner(context);
        spinner.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        int[] location = new int[2];
        v.getLocationOnScreen(location);

        spinner.setPadding(location[0] + v.getWidth() * 3 / 4, 0, 0, 0);
        spinner.setDropDownVerticalOffset(location[1] + v.getHeight());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                stringResource,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        return spinner;
    }
}
