package com.storware.storwaretest;

import java.util.List;

/**
 * Created by Mateusz on 2015-08-27.
 */
public class Processor {


    private Reader reader;
    private Calculator calculator;


    public Processor(Reader reader, Calculator calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    public String calculate() throws Reader.ParseException {
        String result;
        StringBuilder resultBuilder = new StringBuilder();
        while ((result = calculateNext()) != null)
            resultBuilder.append(result).append("\n");

        return resultBuilder.toString();
    }

    private String calculateNext() throws Reader.ParseException {
        List<Operation> readOperationList = reader.readNext();
        if(readOperationList.isEmpty())
            return null;
        return calculator.calculate(readOperationList);
    }


    public void close() {
        reader.close();
    }
}
