package com.reversepolishnotation.rpn;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * operation
 * @author guxixi
 *
 * Created on 2019/4/12 下午6:08
 */
@Data
@AllArgsConstructor
public class Operation {
    private Operator operator;
    private Double value;

    public String getUndoOperation() throws RpnException {
        if (operator.getOperandsNumber() < 1) {
            throw new RpnException(String.format("invalid operation for operator %s", operator.getSymbol()));
        } else if (operator.getOperandsNumber() < 2) {
            return operator.getUndoSymbol();
        } else {
            return String.format("%f %s %f", value, operator.getUndoSymbol(), value);
        }
    }
}
