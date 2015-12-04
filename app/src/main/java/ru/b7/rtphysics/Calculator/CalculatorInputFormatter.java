package ru.b7.rtphysics.Calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import ru.b7.rtphysics.R;

/**
 * Listener to buttons on calc, does some shit when they are pressed
 */
public class CalculatorInputFormatter {

    public CalculatorInputFormatter() {
    }

    public void doAction(View v, CalculatorEditText editText) {

        CalculatorPadButton calcButton = (CalculatorPadButton) v;

        switch (calcButton.getId()) {
            case R.id.calc_button_clr:
                editText.setText(null);
                break;
            case R.id.calc_button_del:
                String s = editText.getText().toString();
                s = s.substring(0, s.length() - 1);
                editText.setText(s);
                break;
            case R.id.calc_button_func:
                break;
            case R.id.calc_button_func_last:
                break;
            case R.id.calc_button_symb:
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
}
