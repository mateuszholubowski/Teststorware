package com.storware.storwaretest;

import java.util.List;

/**
 * Created by Mateusz on 2015-08-27.
 */
public class SimpleCalculator extends Calculator {

    private SimpleCalculator(){

    }

    private static SimpleCalculator instance;

    public static SimpleCalculator getInstance(){
        if(instance == null)
            instance = new SimpleCalculator();

        return instance;
    }

    @Override
    public String calculate(List<Operation> list) {

        int sum = list.get(0).operand;

        for (int i = 1; i < list.size(); i++) {
            Operation o = list.get(i);
            switch (o.operator) {

                case "add":
                    sum += o.operand;
                    break;

                case "multiply":
                    sum *= o.operand;
                    break;

                case "divide":
                    sum /= o.operand;
                    break;

                case "sun":
                    sum -= o.operand;
                    break;
            }
        }

        return "Result = " + sum;
    }
}