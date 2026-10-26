package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Загальний баланс
    public static double calculateTotalBalance(
            List<Transaction> transactions) {

        double balance = 0;

        for (Transaction t : transactions) {
            balance += t.getAmount();
        }

        return balance;
    }

    // Кількість транзакцій за місяць
    public static int countTransactionsByMonth(
            List<Transaction> transactions, String monthYear) {

        int count = 0;

        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(
                    t.getDate(), DATE_FORMAT);

            if (date.format(DateTimeFormatter.ofPattern("MM-yyyy"))
                    .equals(monthYear)) {
                count++;
            }
        }

        return count;
    }

    // 10 найбільших витрат
    public static List<Transaction> findTopExpenses(
            List<Transaction> transactions) {

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Найбільша витрата за місяць
    public static Transaction findLargestExpense(
            List<Transaction> transactions, String monthYear) {

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> getMonthYear(t).equals(monthYear))
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    // Найменша витрата за місяць
    public static Transaction findSmallestExpense(
            List<Transaction> transactions, String monthYear) {

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> getMonthYear(t).equals(monthYear))
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    // Отримання місяця та року транзакції
    private static String getMonthYear(Transaction t) {
        LocalDate date = LocalDate.parse(
                t.getDate(), DATE_FORMAT);

        return date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
    }
}