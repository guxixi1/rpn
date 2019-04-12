package com.reversepolishnotation.rpn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * calculator test
 * @author guxixi
 *
 * Created on 2019/4/12 下午5:46
 */
public class CalculatorTest {

    @Test
    public void testCalculator() {
        Calculator calculator = new Calculator();

        calculator.calculate("5 2", false);
        assertEquals(5, calculator.getStackValues().get(0), 0);
        assertEquals(2, calculator.getStackValues().get(1), 0);

        calculator.calculate("clear 5 2 +", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(7, calculator.getStackValues().get(0), 0);
        calculator.calculate("3 -", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(4, calculator.getStackValues().get(0), 0);

        calculator.calculate("clear 1 2 3 4 5 *", false);
        assertEquals(4, calculator.getStackValues().size());
        calculator.calculate("clear 3 4 -", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(-1, calculator.getStackValues().get(0), 0);


        calculator.calculate("clear 7 12 2 /", false);
        assertEquals(7, calculator.getStackValues().get(0), 0);
        assertEquals(6, calculator.getStackValues().get(1), 0);
        calculator.calculate("*", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(42, calculator.getStackValues().get(0), 0);
        calculator.calculate("4 /", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(10.5, calculator.getStackValues().get(0), 0);

        calculator.calculate("clear 1 2 3 4 5", false);
        calculator.calculate("* * * *", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(120, calculator.getStackValues().get(0), 0);

    }

    @Test
    public void testUndo() {
        Calculator calculator = new Calculator();
        calculator.calculate("5 4 3 2", false);
        assertEquals(4, calculator.getStackValues().size());
        calculator.calculate("undo undo *", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(20, calculator.getStackValues().get(0), 0);
        calculator.calculate("5 *", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(100, calculator.getStackValues().get(0), 0);
        calculator.calculate("undo", false);
        assertEquals(2, calculator.getStackValues().size());
        assertEquals(20, calculator.getStackValues().get(0), 0);
        assertEquals(5, calculator.getStackValues().get(1), 0);
    }

    @Test
    public void testSqrt() {
        Calculator calculator = new Calculator();
        calculator.calculate("2 sqrt", false);
        calculator.calculate("clear 9 sqrt", false);
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(3, calculator.getStackValues().get(0), 0);
    }

    @Test
    public void testInsufficientParameters() {
        Calculator calculator = new Calculator();
        try {
            calculator.calculate("1 2 3 * 5 + * * 6 5", false);
        } catch (RpnException e) {
            assertEquals("operator * (position: 8): insufficient parameters", e.getMessage());
        }
        assertEquals(1, calculator.getStackValues().size());
        assertEquals(11, calculator.getStackValues().get(0), 0);
    }

    @Test(expected = RpnException.class)
    public void testInvalidCharacters() {
        Calculator calculator = new Calculator();
        calculator.calculate("2 a +", false);
    }
}
