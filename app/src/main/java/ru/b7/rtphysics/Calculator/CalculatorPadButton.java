package ru.b7.rtphysics.Calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * meow?
 */
public class CalculatorPadButton extends Button {
    public CalculatorPadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        CalculatorMainActivity calc = (CalculatorMainActivity) context;
        setOnClickListener(calc.mListener);
    }
}
