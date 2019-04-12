package com.reversepolishnotation.rpn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Stack;

/**
 * rpn application
 * @author guxixi
 *
 * Created on 2019/4/12 下午7:43
 */
public class rpnApplication {
    public static void main(String[] args) throws IOException {
        InputStreamReader input1 = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(input1);

        Calculator calculator = new Calculator();
        System.out.println("Please enter your expression, enter 'exit' to quit");

        boolean existing = true;
        while (existing) {
            String name = in.readLine();
            if ("exit".equals(name)) {
                existing = false;
            } else {
                try {
                    calculator.calculate(name, false);
                } catch (RpnException e) {
                    System.out.println(e.getMessage());
                }
                DecimalFormat fmt = new DecimalFormat("0.##########");
                Stack<Double> stack = calculator.getStackValues();
                System.out.print("Stack: ");
                for (Double value : stack) {
                    System.out.print(fmt.format(value));
                    System.out.print(" ");
                }
                System.out.printf("%n");
            }
        }
    }
}
