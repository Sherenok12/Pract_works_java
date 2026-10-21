package org.example;

import java.util.Scanner;

public class Main {

    public static double parseNumber(String text) throws InvalidInputException {
        try {
            return Double.parseDouble(text.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new InvalidInputException("'" + text + "' — це не число!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = parseNumber(scanner.nextLine());

            System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
            String op = scanner.nextLine().trim();

            double result;

            if (op.equals("sqrt")) {
                result = calc.sqrt(a);
            } else {
                System.out.print("Введіть друге число: ");
                double b = parseNumber(scanner.nextLine());

                switch (op) {
                    case "+": result = calc.add(a, b); break;
                    case "-": result = calc.subtract(a, b); break;
                    case "*": result = calc.multiply(a, b); break;
                    case "/": result = calc.divide(a, b); break;
                    default:
                        throw new InvalidInputException("Невідома операція: " + op);
                }
            }

            System.out.println("Результат: " + result);

        } catch (ArithmeticException e) {
            System.out.println("Помилка обчислення: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Помилка вводу: " + e.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено.");
            scanner.close();
        }
    }
}