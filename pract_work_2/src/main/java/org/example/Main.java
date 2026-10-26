
package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Читання транзакцій за посиланням
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new URL(filePath).openStream(),
                        StandardCharsets.UTF_8))) {

            transactions = TransactionCSVReader.readTransactions(br);
        }

        // Розрахунок загального балансу
        double totalBalance =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        // Підрахунок транзакцій за січень 2023 року
        String monthYear = "01-2023";

        int count = TransactionAnalyzer.countTransactionsByMonth(
                transactions, monthYear);

        // Пошук 10 найбільших витрат
        List<Transaction> topExpenses =
                TransactionAnalyzer.findTopExpenses(transactions);

        // Пошук найбільшої та найменшої витрат за місяць
        Transaction largest =
                TransactionAnalyzer.findLargestExpense(
                        transactions, monthYear);

        Transaction smallest =
                TransactionAnalyzer.findSmallestExpense(
                        transactions, monthYear);

        // Виведення звітів
        TransactionReportGenerator.printBalanceReport(totalBalance);

        TransactionReportGenerator.printTransactionsCountByMonth(
                monthYear, count);

        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        TransactionReportGenerator.printExpenseExtremesReport(
                monthYear, largest, smallest);

        // Звіт за категоріями та місяцями
        TransactionReportGenerator.printExpensesByCategoryAndMonth(
                transactions);
    }
}
