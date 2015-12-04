package ru.b7.rtphysics.Calculator;

import java.util.Stack;

/**
 * Calculating some shit, input goes to reverse polish notation and back to answer
 */
public class CalculatorCalculatingRPN {

    public CalculatorCalculatingRPN() {

    }

    public String doTransitionInRPN(String input) {
        String RPNAnswer = "";
        char[] currentChar = input.toCharArray();
        Stack stack = new Stack();

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(currentChar[i])) {
                RPNAnswer += currentChar[i];
            } else if (currentChar[i] == '.') {
                RPNAnswer += currentChar[i];
            } else if (currentChar[i] == '(') {
                stack.push(currentChar[i]);
            } else if (currentChar[i] == ')') {
                while (stack.peek().toString().charAt(0) != '(')
                    RPNAnswer += stack.pop();
                stack.pop();
            } else if (stack.isEmpty() || checkPriority(currentChar[i], stack)) {
                RPNAnswer += " ";
                stack.push(currentChar[i]);
            } else {
                RPNAnswer += stack.pop();
                stack.push(currentChar[i]);
            }
        }

        while (!stack.isEmpty()) {
            RPNAnswer += stack.pop();
        }

        return RPNAnswer;
    }

    public String calculate(String RPNInput) {
        Stack stack = new Stack();
        double[] numbers;
        double result = 0;
        char[] currentChar = RPNInput.toCharArray();

        for (int i = 0; i < RPNInput.length(); i++) {
            if (Character.isDigit(currentChar[i]) || currentChar[i] == ' ') {
                stack.push(currentChar[i]);
            } else {
                numbers = createNumbers(stack);
                switch (currentChar[i]) {
                    case '+': result = numbers[0] + numbers[1];
                        break;
                    case '-': result = numbers[0] - numbers[1];
                        break;
                    case '*': result = numbers[0] * numbers[1];
                        break;
                    case '/': result = numbers[0] / numbers[1];
                        break;
                    case '^': result = Math.pow(numbers[0], numbers[1]);
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

    private double[] createNumbers(Stack stack) {
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

        while (!stack.isEmpty()) {
            output += stack.pop();
        }

        char[] chars = output.toCharArray();

        output = "";
        for (int i = chars.length - 1; i >= 0; i--)
            output += chars[i];

        return output;
    }
}
