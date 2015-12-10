package ru.b7.rtphysics.Calculator.FCalculator;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.b7.rtphysics.Calculator.CalculatorCalculatingRPN;
import ru.b7.rtphysics.R;

/**
 * generates scrollview with all elements
 */
public class FCalculatorLayoutView {

    private Context context;
    private CalculatorCalculatingRPN calc;
    private String input = null;
    private char[] variables = null;
    private int id = 0;
    private LinearLayout mainLayout;

    public FCalculatorLayoutView(Context context) {
        this.context = context;
        calc = new CalculatorCalculatingRPN(context);
    }

    public View createScrollView(int numberOfFormulas) {

        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mainLayout = (LinearLayout) createLinearLayout();
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        mainLayout.addView(createTitle());

        for (int i = 0; i < numberOfFormulas; i++) {
            LinearLayout linearLayout = (LinearLayout) createLinearLayout();
            linearLayout = (LinearLayout) fillLayout(linearLayout, i);
            mainLayout.addView(linearLayout);
        }

        scrollView.addView(mainLayout);

        return scrollView;
    }

    private View createLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0, 0, 0, 20);

        return linearLayout;
    }

    private View fillLayout(LinearLayout linearLayout, int position) {
        TextView nameOfVariable = new TextView(context);
        nameOfVariable.setText(String.valueOf(variables[position]) + " = ");
        nameOfVariable.setTextSize(20);
        nameOfVariable.setTextColor(ContextCompat.getColor(context,
                R.color.fcalculatorVariablesTextColor));

        EditText variableInput = new EditText(context);
        variableInput.setTextSize(20);
        variableInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        variableInput.setId(id++);
        variableInput.setTextColor(ContextCompat.getColor(context, R.color.fcalculatorDefaultTextColor));

        linearLayout.addView(nameOfVariable);
        linearLayout.addView(variableInput);

        return linearLayout;
    }

    private View createTitle() {
        LinearLayout linearLayout = (LinearLayout) createLinearLayout();

        TextView formula = new TextView(context);
        formula.setText(input);
        formula.setTextSize(30);

        linearLayout.addView(formula);

        return linearLayout;
    }

    public int getNumberOfVariables() {

        int numberOfVariables = 0;

        char[] inputArray = input.toCharArray();
        for (int i = 0; i < inputArray.length; i++) {
            if (Character.isLetter(inputArray[i])) {
                if (!calc.isFunction(inputArray, i)) {
                    numberOfVariables++;
                } else {
                    while (inputArray[i] != '(') i++;
                }
            }
        }

        variables = new char[numberOfVariables];
        getVariables();
        return numberOfVariables;
    }


    private void getVariables() {

        char[] inputArray = input.toCharArray();
        int counter = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (Character.isLetter(inputArray[i])) {
                if (!calc.isFunction(inputArray, i)) {
                    variables[counter] = inputArray[i];
                    counter++;
                } else {
                    while (inputArray[i] != '(') i++;
                }
            }
        }
    }

    public String getFormula(String input, int number) {
        int resource = context.getResources().getIdentifier(input, "array", context.getPackageName());

        this.input = context.getResources().getStringArray(resource)[number];

        return this.input;
    }

    public String[] getUserInput(int numberOfVariables) {
        String[] userInput = new String[numberOfVariables];
        int counter = 0;
        ArrayList<View> allViews = getAllChildren(mainLayout);

        for (View child : allViews) {
            if (child instanceof EditText) {
                ((EditText) child).setTextColor(ContextCompat.getColor(context,
                        R.color.fcalculatorDefaultTextColor));
                userInput[counter] = ((EditText) child).getText().toString();
                counter++;
            }
        }

        return userInput;
    }

    public int checkUserInput(String[] userInput) {
        int counter = -2;
        boolean bool = false;

        for (int i = 0; i < userInput.length; i++) {
            if (userInput[i].isEmpty()) {
                if (!bool) {
                    bool = true;
                } else {
                    return -1;
                }
                counter = i;
            }
        }

        return counter;
    }

    private ArrayList<View> getAllChildren(View v) {
        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    public void inputAnswer(String answer) {
        ArrayList<View> allViews = getAllChildren(mainLayout);

        for (View child : allViews) {
            if (child instanceof EditText)
                if (((EditText) child).getText().toString().isEmpty()) {
                    ((EditText) child).setText(answer);
                    ((EditText) child).setTextColor(ContextCompat.getColor(context,
                            R.color.fcalculatorAnswerTextColor));
                }
        }
    }
}