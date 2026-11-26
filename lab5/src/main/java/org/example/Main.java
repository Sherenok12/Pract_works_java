package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TestAutomaton automaton = new TestAutomaton();

        System.out.print("Введіть значення: ");
        String input = scanner.nextLine();

        State result = automaton.process(input);

        System.out.println("Кінцевий стан: " + result);

        if (result == State.F) {
            System.out.println("Слово TEST було знайдено!");
        } else {
            System.out.println("Слово TEST не було знайдено");
        }

        scanner.close();
    }
}
