package com.storware.storwaretest;

/**
 * Created by Mateusz on 2015-08-27.
 */
public class Operation {
    public static final String END_OPERATOR = "apply";
    public static final String[] operators = {"add", "multiply", END_OPERATOR, "sub", "divide"};

    String operator;
    int operand;

    public Operation(String s, int i) {
        operator = s;
        operand = i;
    }
}