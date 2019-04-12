package com.reversepolishnotation.rpn;

import org.junit.Test;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

/**
 * operator test
 * @author guxixi
 *
 * Created on 2019/4/12 下午7:36
 */
public class OperatorTest {

    @Test
    public void testCalculate() throws RpnException {
        double firstNum = 5.0;
        double secondNum = 3.0;
        assertEquals(secondNum + firstNum, Operator.getEnum("+").rpnCalculator(firstNum, secondNum), 0);
        assertEquals(secondNum - firstNum, Operator.getEnum("-").rpnCalculator(firstNum, secondNum), 0);
        assertEquals(secondNum * firstNum, Operator.getEnum("*").rpnCalculator(firstNum, secondNum), 0);
        assertEquals(secondNum / firstNum, Operator.getEnum("/").rpnCalculator(firstNum, secondNum), 0);
        assertEquals(sqrt(secondNum), Operator.getEnum("sqrt").rpnCalculator(secondNum, null), 0);
    }

    @Test(expected = RpnException.class)
    public void testDivideByZero() {
        Operator.getEnum("/").rpnCalculator(0.0, 2.0);
    }
}
