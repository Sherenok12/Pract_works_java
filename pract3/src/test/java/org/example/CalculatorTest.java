package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calc = new Calculator();


    @Test
    void testAdd() throws InvalidInputException {
        assertEquals(5.0, calc.add(2, 3));
    }

    @Test
    void testSubtract() throws InvalidInputException {
        assertEquals(-1.0, calc.subtract(2, 3));
    }

    @Test
    void testMultiply() throws InvalidInputException {
        assertEquals(6.0, calc.multiply(2, 3));
    }

    @Test
    void testDivide() throws InvalidInputException {
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    @Test
    void testStringInsteadOfNumber() {
        assertThrows(InvalidInputException.class, () -> Main.parseNumber("abc"));
    }

    @Test
    void testValidStringParsed() throws InvalidInputException {
        assertEquals(3.5, Main.parseNumber("3.5"));
    }

    @Test
    void testSqrtOfNegative() {
        assertThrows(InvalidInputException.class, () -> calc.sqrt(-4));
    }

    @Test
    void testSqrtOfPositive() throws InvalidInputException {
        assertEquals(3.0, calc.sqrt(9));
    }

    @Test
    void testNaNInput() {
        assertThrows(InvalidInputException.class, () -> calc.add(Double.NaN, 1));
    }

    @Test
    void testInfinityInput() {
        assertThrows(InvalidInputException.class, () -> calc.multiply(Double.POSITIVE_INFINITY, 2));
    }
}