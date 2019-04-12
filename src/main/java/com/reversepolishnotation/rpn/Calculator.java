package com.reversepolishnotation.rpn;

import java.util.Stack;
/**
 * calculator
 * @author guxixi
 *
 * Created on 2019/4/12 下午4:20
 */
public class Calculator {
    private Stack<Double> outputStack = new Stack<>();
    private Stack<Operation> tempStack = new Stack<>();
    private int currentInputIndex;

    public Stack<Double> getStackValues() {
        return outputStack;
    }

    public void calculate(String input, boolean isUndoOperation) throws RpnException {
        if (input == null) {
            throw new RpnException("Input cannot be null");
        }
        currentInputIndex = 0;
        String[] results = input.split("\\s+");
        for (String result : results) {
            currentInputIndex++;
            doCalculate(result, isUndoOperation);
        }
    }

    private void doCalculate(String input, boolean isUndoOperation) throws RpnException {
        Double value = convertToDouble(input);
        if (value == null) {
            processOperator(input, isUndoOperation);
        } else {
            outputStack.push(Double.parseDouble(input));
            if (!isUndoOperation) {
                tempStack.push(null);
            }
        }
    }

    private Double convertToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    private void processOperator(String operatorString, boolean isUndoOperation) throws RpnException {
        if (outputStack.isEmpty()) {
            throw new RpnException("empty stack");
        }

        Operator operator = Operator.getEnum(operatorString);
        if (operator == null) {
            throw new RpnException("invalid operator");
        }
        if (operator.getOperandsNumber() > outputStack.size()) {
            throwInvalidOperand(operatorString);
        }
        if (operator == Operator.CLEAR) {
            clearStacks();
            return;
        }
        if (operator == Operator.UNDO) {
            undoOperation();
            return;
        }
        Double firstNum = outputStack.pop();
        Double secondNum = (operator.getOperandsNumber() > 1) ? outputStack.pop() : null;
        Double result = operator.rpnCalculator(firstNum, secondNum);
        if (result != null) {
            outputStack.push(result);
            if (!isUndoOperation) {
                tempStack.push(new Operation(Operator.getEnum(operatorString), firstNum));
            }
        }
    }

    private void throwInvalidOperand(String operator) throws RpnException {
        throw new RpnException(
                String.format("operator %s (position: %d): insufficient parameters", operator, currentInputIndex));
    }

    private void clearStacks() {
        outputStack.clear();
        tempStack.clear();
    }

    private void undoOperation() throws RpnException {
        if (tempStack.isEmpty()) {
            throw new RpnException("no operations to undo");
        }
        Operation lastOperation = tempStack.pop();
        if (lastOperation == null) {
            outputStack.pop();
        } else {
            calculate(lastOperation.getUndoOperation(), true);
        }
    }
}
