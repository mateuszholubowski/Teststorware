package com.storware.storwaretest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mateusz on 2015-08-27.
 */
public class Reader {

    protected Scanner mScanner;

    public Reader(Scanner scanner) {
        this.mScanner = scanner;
    }

    public List<Operation> readNext() throws ParseException {
        List<Operation> operations = new ArrayList<>();

        while (mScanner.hasNextLine()) {
            String line = mScanner.nextLine();
            System.out.println(line);
            Operation operation = parse(line);
            operations.add(operation);

            if (operation.operator.equals(Operation.END_OPERATOR)) {
                return sort(operations);
            }
        }
        if (!operations.isEmpty())
            throw new ParseException("No end operator:" + Operation.END_OPERATOR);
        else return new ArrayList<>();
    }

    protected List<Operation> sort(List<Operation> operations) {

        Operation o = operations.remove(operations.size() - 1);
        operations.add(0, o);
        return operations;
    }

    protected Operation parse(String line) throws ParseException {
        String[] splittedLine = line.split(" ");
        if (splittedLine != null && splittedLine.length == 2 && validatePrefix
                (splittedLine[0]) && validatePostfix(splittedLine[1])) {
            return new Operation(splittedLine[0], Integer.parseInt(splittedLine[1]));
        } else {
            throw new ParseException(line + " has not valid syntax");
        }
    }

    protected boolean validatePostfix(String line) {
        try {
            Integer.parseInt(line);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return false;
        }
        return true;
    }

    protected boolean validatePrefix(String prefix) {
        for (String o : Operation.operators) {
            if (o.equals(prefix))
                return true;
        }
        return false;
    }

    public void close() {
        mScanner.close();
    }


    public static class ParseException extends Exception {

        private String message;

        ParseException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

}
