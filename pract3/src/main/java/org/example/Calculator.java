package org.example;

public class Calculator {

    private void check(double x) throws InvalidInputException {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new InvalidInputException("Некоректне число: " + x);
        }
    }

    public double add(double a, double b) throws InvalidInputException {
        check(a);
        check(b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        check(a);
        check(b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        check(a);
        check(b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        check(a);
        check(b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе!");
        }
        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        check(a);
        if (a < 0) {
            throw new InvalidInputException("Не можна взяти корінь з від'ємного числа: " + a);
        }
        return Math.sqrt(a);
    }
}