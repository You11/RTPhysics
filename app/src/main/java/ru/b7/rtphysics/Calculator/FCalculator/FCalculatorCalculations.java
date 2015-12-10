package ru.b7.rtphysics.Calculator.FCalculator;

import android.content.Context;

import ru.b7.rtphysics.Calculator.CalculatorCalculatingRPN;
import ru.b7.rtphysics.R;

/**
 * Calculations for FCalc
 */
public class FCalculatorCalculations {

    Context context;
    String formula;

    public FCalculatorCalculations(Context context) {
        this.context = context;
    }

    public void getInput(String input) {
        formula = input;
    }

    public String calculateAnswer(String[] input) {
        replaceVariablesWithInput(input);
        String partWithX = divideFormula();

        boolean[] operations = {true, true, true, true, true};

        isolateVariable(partWithX, 0, operations);
        CalculatorCalculatingRPN calc = new CalculatorCalculatingRPN(context);
        formula = calc.doTransitionInRPN(formula);
        formula = calc.calculate(formula);

        return formula;
    }

    private void replaceVariablesWithInput(String[] input) {
        char[] chars = formula.toCharArray();
        formula = "";
        int counter = 0;
        CalculatorCalculatingRPN calc = new CalculatorCalculatingRPN(context);

        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (!calc.isFunction(chars, i)) {
                    if (input[counter].isEmpty()) {
                        formula += "x";
                        counter++;
                    } else {
                        formula += input[counter];
                        counter++;
                    }
                } else {
                    while (chars[i] != '(') {
                        formula += chars[i];
                        i++;
                    }
                    formula += chars[i];
                }
            } else {
                formula += chars[i];
            }
        }
    }

    private String divideFormula() {
        char[] chars = formula.toCharArray();
        int positionOfEquals = -1;
        int positionOfX = -1;
        formula = "";
        String partOfFormulaWithX = "";

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '=') {
                positionOfEquals = i;
            }

            if (chars[i] == 'x') {
                positionOfX = i;
            }

            if (positionOfEquals != -1 && positionOfX != -1) {
                break;
            }
        }

        if (positionOfX < positionOfEquals) {
            for (int i = 0; i < positionOfEquals; i++) {
                partOfFormulaWithX += chars[i];
            }

            for (int i = positionOfEquals + 1; i < chars.length; i++) {
                formula += chars[i];
            }
        } else {
            for (int i = 0; i < positionOfEquals; i++) {
                formula += chars[i];
            }

            for (int i = positionOfEquals + 1; i < chars.length; i++) {
                partOfFormulaWithX += chars[i];
            }
        }

        return partOfFormulaWithX;
    }

    private void isolateVariable(String input, int operation, boolean[] operationsExists) {

        /**
         * '+' == 0
         * '-' == 1
         * '*' == 2
         * '/' == 3
         * '^' == 4
         * fn = 5
         */

        if (input.equals("x")) {
            return;
        }

        char[] charsInput = input.toCharArray();
        char operationChar;
        char rOperationChar;

        //если скобки с обоих сторон
        if (charsInput[0] == '(' && charsInput[charsInput.length - 1] == ')') {
            input = "";

            for (int i = 1; i < charsInput.length - 1; i++) {
                input += charsInput[i];
            }

            isolateVariable(input, operation, operationsExists);
            return;
        }

        switch (operation) {
            case 0:
                if (!operationsExists[0]) break;
                operationChar = '+';
                rOperationChar = '-';

                isolatorForBasicOperations(charsInput,
                        operationChar,
                        rOperationChar,
                        operationsExists,
                        operation);
                return;
            case 1:
                if (!operationsExists[1]) break;
                operationChar = '-';
                rOperationChar = '+';

                isolatorForBasicOperations(charsInput,
                    operationChar,
                    rOperationChar,
                    operationsExists,
                    operation);
                return;
            case 2:
                if (!operationsExists[2]) break;
                operationChar = '*';
                rOperationChar = '/';
                isolatorForBasicOperations(charsInput,
                        operationChar,
                        rOperationChar,
                        operationsExists,
                        operation);
                return;
            case 3:
                if (!operationsExists[3]) break;
                operationChar = '/';
                rOperationChar = '*';
                isolatorForBasicOperations(charsInput,
                        operationChar,
                        rOperationChar,
                        operationsExists,
                        operation);
                return;
            case 4:
                if (!operationsExists[4]) break;
                operationChar = '^';
                isolatorForBasicOperations(charsInput,
                        operationChar,
                        operationChar,
                        operationsExists,
                        operation);
                return;
            default:
                CalculatorCalculatingRPN calc = new CalculatorCalculatingRPN(context);
                String function = calc.getFunction(charsInput, 0);
                switch (function) {
                    case "sin":
                    case "cos":
                    case "tg":
                    case "ctg":
                        getIsolatorForTrigonometry(charsInput, function, operationsExists);
                        return;
                    case "ln":
                    case "log":
                        getIsolatorForLog(charsInput, function, operationsExists);
                }
        }
    }

    private void isolatorForBasicOperations(char[] charsInput,
                                            char operationChar,
                                            char rOperationChar,
                                            boolean[] operationsExists,
                                            int operation) {
        int bracketCounter = 0;
        boolean isInBrackets = false;
        boolean isX = false;
        String input;

        for (int i = 0; i < charsInput.length; i++) {
            if (charsInput[i] == '(') bracketCounter++;
            if (charsInput[i] == ')') bracketCounter--;
            if (charsInput[i] == 'x') isX = true;

            if (charsInput[i] == operationChar) {
                if (bracketCounter == 0) {
                    if (isX) {
                        if (operationChar == '^') {
                            input = "";
                            for (int j = 0; j < i; j++) {
                                input += charsInput[j];
                            }

                            String prevInput = formula;

                            formula = "(" + prevInput + ")" + rOperationChar + "(1/(";

                            for (int j = i + 1; j < charsInput.length; j++){
                                formula += charsInput[j];
                            }

                            formula += "))";
                            isolateVariable(input, 0, operationsExists);
                            return;

                        } else {
                            input = "";
                            for (int j = 0; j < i; j++) {
                                input += charsInput[j];
                            }

                            formula += rOperationChar;

                            for (int j = i + 1; j < charsInput.length; j++) {
                                formula += charsInput[j];
                            }

                            isolateVariable(input, 0, operationsExists);
                            return;
                        }
                    } else {
                        if (charsInput[i] == '^') {
                            input = "";
                            String prevFormula = formula;
                            formula = "log(" + prevFormula + ")/log(";

                            for (int j = 0; j < i; j++) {
                                formula += charsInput[j];
                            }

                            formula += ")";

                            for (int j = i + 1; j < charsInput.length; j++) {
                                input += charsInput[j];
                            }

                            isolateVariable(input, 0, operationsExists);
                            return;

                        } else {
                            input = "";
                            formula += rOperationChar;

                            for (int j = 0; j < i; j++) {
                                formula += charsInput[j];
                            }

                            for (int j = i + 1; j < charsInput.length; j++) {
                                input += charsInput[j];
                            }

                            isolateVariable(input, 0, operationsExists);
                            return;
                        }
                    }
                } else {
                    isInBrackets = true;
                }
            }
        }

        if (!isInBrackets) {
            operationsExists[operation] = false;
        }

        input = String.valueOf(charsInput);
        isolateVariable(input, operation + 1, operationsExists);
    }

    private void getIsolatorForTrigonometry(char[] charInput, String function, boolean[] operationsExists) {
        String input = "";
        String[] functions = context.getResources().getStringArray(R.array.functions_array);
        String reverseFunction = "";

        function += "(";
        for (int i = 1; i < 5; i++) {
            if (function.equals(functions[i])) {
                reverseFunction = context.getResources().getStringArray(R.array.functions_array_reverse)[i];
            }
        }

        String prevFormula = formula;
        formula = reverseFunction + prevFormula + ")";

        for (int i = function.length(); i < charInput.length - 1; i++) {
            input += charInput[i];
        }

        isolateVariable(input, 0, operationsExists);
    }

    private void getIsolatorForLog(char[] charInput, String function, boolean[] operatorsExists) {
        String input = "";
        String prevFormula = formula;

        switch (function) {
            case "ln":
                formula = "e^(" + prevFormula + ")";
                break;
            case "log":
                formula = "10^(" + prevFormula + ")";
        }

        for (int i = function.length(); i < charInput.length; i++) {
            input += charInput[i];
        }

        isolateVariable(input, 0, operatorsExists);
    }
}
