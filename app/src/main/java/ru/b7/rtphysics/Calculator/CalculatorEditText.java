package ru.b7.rtphysics.Calculator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import ru.b7.rtphysics.R;

/**
 * Input/output display string for calc.
 */
public class CalculatorEditText extends EditText {

    private int selection = 0;

    public CalculatorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    private void setUp() {
        setCustomSelectionActionModeCallback(new NoTextSelectionMode());
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getRootView().getWindowToken(), 0);
        setTextSize(40);
        setGravity(Gravity.END);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setSelection(s.length() - selection);
            }
        });
    }

    public void insert(String input) {
        CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);
        String s = editText.getText().toString();
        String temp1 = s.substring(0, getSelectionStart());
        String temp2 = s.substring(getSelectionStart(), s.length());
        s = temp1 + input + temp2;
        selection = temp2.length();
        editText.setText(s);
    }

    public void deleteLastSymbol() {
        CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);
        String s = editText.getText().toString();

        if (getSelectionStart() == s.length()) {
            if (!s.equals("")) {
                s = s.substring(0, s.length() - 1);
            } else return;
        } else {
            String temp1 = s.substring(0, getSelectionStart());
            String temp2 = s.substring(getSelectionStart() + 1, s.length());
            s = temp1 + temp2;
            selection = temp2.length();
        }
        editText.setText(s);
    }

    public void clearInput() {
        CalculatorEditText editText = (CalculatorEditText) findViewById(R.id.editText);
        selection = 0;
        editText.setText("");
    }

    class NoTextSelectionMode implements ActionMode.Callback {
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Prevents the selection action mode on double tap.
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {}

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }
}
