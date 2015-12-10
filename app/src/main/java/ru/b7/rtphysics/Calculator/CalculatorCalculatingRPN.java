package ru.b7.rtphysics.Calculator;

import android.content.Context;
import android.util.Log;

import java.util.Stack;

import ru.b7.rtphysics.R;

/**
 * Calculating some shit, input goes to reverse polish notation and then calculates back to answer
 */
public class CalculatorCalculatingRPN {

    private Context context;
    private String errorMessage;

    public CalculatorCalculatingRPN(Context context) {
        this.context = context;
        errorMessage = context.getResources().getString(R.string.calculator_incorrect_input);
    }

    public String doTransitionInRPN(String input) {
        String RPNAnswer = "";
        char[] currentChar = input.toCharArray();
        int lastCharWasConstant = 0;
        Stack stack = new Stack();

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(currentChar[i]) || currentChar[i] == '.') {
            //цифра или точка
                RPNAnswer += currentChar[i];

            } else if (Character.isLetter(currentChar[i])) {
            //константы и функции
                int constNumber;
                if (i != currentChar.length - 1 && Character.isLetter(currentChar[i + 1])) {
                    constNumber = isConstant((currentChar[i]), currentChar[i + 1]);
                    if (constNumber != -1) {
                        RPNAnswer += getConstant(constNumber);
                        lastCharWasConstant = 2;
                    } else {
                        String function = getFunction(currentChar, i);
                        stack.push('(');
                        stack.push(function);

                        for (int j = 0; j < function.length(); j++)
                            i++;
                    }
                } else {
                    constNumber = isConstant(currentChar[i]);
                    if (constNumber != -1) {
                        RPNAnswer += getConstant(constNumber);
                        lastCharWasConstant = 2;
                    }
                }

            } else if (currentChar[i] == '(') {
            //открывающаяся скобка, всегда идет в стэк
                stack.push(currentChar[i]);

            } else if (currentChar[i] == ')') {
            //закрывающаяся скобка, пока не встретит откр.скобку, выпиливает все из стэка
                while (stack.peek().toString().charAt(0) != '(')
                    RPNAnswer += stack.pop();
                stack.pop();

            } else if (currentChar[i] == '-' && (i == 0
                    || !Character.isDigit(currentChar[i - 1])
                    && lastCharWasConstant != -1)){
                //отрицательное число
                RPNAnswer += "0 ";
                stack.push(currentChar[i]);

            } else if (stack.isEmpty() || checkPriority(currentChar[i], stack)) { //для всего остального
                RPNAnswer += " ";
                stack.push(currentChar[i]);

            } else {
                RPNAnswer += stack.pop();
                stack.push(currentChar[i]);
            }

            if (lastCharWasConstant > 0) lastCharWasConstant--;
        }

        while (!stack.isEmpty()) {
            RPNAnswer += stack.pop();
        }

        Log.d("meow", RPNAnswer);

        return RPNAnswer;
    }

    public String calculate(String RPNInput) {
        Stack stack = new Stack();
        double[] numbers;
        double result = 0;
        char[] currentChar = RPNInput.toCharArray();

        for (int i = 0; i < RPNInput.length(); i++) {
            if (Character.isDigit(currentChar[i])
                    || currentChar[i] == ' '
                    || currentChar[i] == '.') {
                stack.push(currentChar[i]);
            } else {
                if (!Character.isLetter(currentChar[i])) {
                    numbers = getTwoNumbers(stack);
                    switch (currentChar[i]) {
                        case '+':
                            result = numbers[0] + numbers[1];
                            break;
                        case '-':
                            result = numbers[0] - numbers[1];
                            break;
                        case '*':
                            result = numbers[0] * numbers[1];
                            break;
                        case '/':
                            if (numbers[1] == 0) return errorMessage;
                            result = numbers[0] / numbers[1];
                            break;
                        case '^':
                            if (numbers[1] < 1 && numbers[0] < 0) return errorMessage;
                            result = Math.pow(numbers[0], numbers[1]);
                            break;
                    }

                } else if (isFunction(currentChar, i)) {
                    String function = getFunction(currentChar, i);

                    double number = Double.parseDouble(getString(stack));

                    for (int j = 0; j < function.length() - 1; j++)
                        i++;

                    switch (function) {
                        case "sin":
                            result = Math.sin(number);
                            break;

                        case "cos":
                            result = Math.cos(number);
                            break;

                        case "tg":
                            result = Math.tan(number);
                            break;

                        case "ctg":
                            result = 1 / Math.tan(number);
                            break;

                        case "log":
                            if (number <= 0) return errorMessage;
                            result = Math.log10(number);
                            break;

                        case "ln":
                            if (number <= 0) return errorMessage;
                            result = Math.log(number);
                    }
                }

                String s = String.valueOf(result);
                char[] chars = s.toCharArray();
                boolean isInteger = isInteger(chars);

                if (isInteger) {
                    for (int j = 0; chars[j] != '.'; j++) {
                        stack.push(chars[j]);
                    }
                } else {
                    for (int j = 0; j < s.length(); j++) {
                        stack.push(chars[j]);
                    }
                }

                if (i != RPNInput.length() - 1 && Character.isDigit(currentChar[i + 1])) {
                    stack.push(' ');
                }
            }
        }


        return getString(stack);
    }

    private static boolean checkPriority(char currentChar, Stack stack) {
        int a = 0;
        int b = 0;

        switch (currentChar) {
            case '^':
                a = 3;
                break;
            case '*':
            case '/':
                a = 2;
                break;
            case '+':
            case '-':
                a = 1;
                break;
        }

        switch (stack.peek().toString()) {
            case "^":
                b = 3;
            case "*":
            case "/":
                b = 2;
                break;
            case "+":
            case "-":
                b = 1;
                break;
        }

        return a > b;
    }

    private double[] getTwoNumbers(Stack stack) {
        String temp = "";
        double[] numbers = new double[2];
        int counter = 0;

        while (!stack.isEmpty()) {
            if (stack.peek().toString().charAt(0) == ' ') counter++;
            if (counter == 2) break;
            temp += stack.pop();
        }

        char[] charArray = temp.toCharArray();

        temp = "";

        int j;
        for (j = charArray.length - 1; charArray[j] != ' '; j--) {
            temp += charArray[j];
        }

        numbers[0] = Double.parseDouble(temp);
        j--;
        temp = "";

        while (j >= 0) {
            temp += charArray[j];
            j--;
        }

        numbers[1] = Double.parseDouble(temp);

        return numbers;
    }

    private boolean isInteger(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                for (int k = i + 1; k < chars.length; k++) {
                    if (chars[k] != '0') return false;
                }
                return true;
            }
        }
        return true;
    }

    private String getString(Stack stack) {
        String output = "";

        while (!stack.isEmpty() && !stack.peek().toString().equals(" ")) {
            output += stack.pop();
        }

        char[] chars = output.toCharArray();

        output = "";
        for (int i = chars.length - 1; i >= 0; i--)
            output += chars[i];

        return output;
    }

    public boolean isFunction(char[] chars, int position) {
        return Character.isLetter(chars[position])
                && position != chars.length - 1
                && Character.isLetter(chars[position + 1]);
    }

    public String getFunction(char[] chars, int position) {
        String func = "";
        while (position != chars.length && (Character.isLetter(chars[position]))) {
            func += chars[position];
            position++;
        }

        return func;
    }

    private int isConstant(char symbol) {
        String[] array = context.getResources().getStringArray(R.array.symbols_array);

        for (int i = 1; i < array.length; i++)
            if (String.valueOf(symbol).equals(array[i])) {
                return i;
            }

        return -1;
    }

    private int isConstant(char symbol1, char symbol2) {
        String[] array = context.getResources().getStringArray(R.array.symbols_array);
        String symbolString = String.valueOf(symbol1) + String.valueOf(symbol2);

        for (int i = 1; i < array.length; i++) {
            if (symbolString.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    private String getConstant(int position) {
        String[] array = context.getResources().getStringArray(R.array.symbols_array_values);

        return array[position];
    }
}
