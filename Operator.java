package com.reversepolishnotation.rpn;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
/**
 * operator
 * @author guxixi
 *
 * Created on 2019/4/12 下午4:35
 */
@AllArgsConstructor
@Getter
public enum Operator {
    ADDITION("+", "-", 2), SUBTRACTION("-", "+", 2), MULTIPLICATION("*", "/", 2), DIVISION("/", "*", 2),
    SQRT("sqrt", "pow", 1), UNDO("undo", null, 0), CLEAR("clear", null, 0);

    private String symbol;
    private String undoSymbol;
    private int operandsNumber;

    private static final Map<String, Operator> map = new HashMap<>();

    static {
        for (Operator o : values()) {
            map.put(o.getSymbol(), o);
        }
    }

    public static Operator getEnum(String value) {
        return map.get(value);
    }

    public Double rpnCalculator(Double firstNum, Double secondNum) throws RpnException{
        Double result;
        switch (symbol) {
            case "+":
                result = secondNum + firstNum;
                break;
            case "-":
                result = secondNum - firstNum;
                break;
            case "*":
                result = secondNum * firstNum;
                break;
            case "/":
                if (firstNum == 0) {
                    throw new RpnException("divider can not be 0");
                }
                result = secondNum / firstNum;
                break;
            case "sqrt":
                result = StrictMath.sqrt(firstNum);
                break;
            case "undo":
                throw new RpnException("invalid operation");
            case "clear":
                throw new RpnException("invalid operation");
            default:
                throw new RpnException("invalid operation");
        }
        return result;
    }
}