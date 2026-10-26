package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions;

        try (BufferedReader br = TransactionReader.openReader(filePath)) {
            transactions = TransactionCSVReader.readTransactions(br);
        } catch (IOException e) {
            System.err.println("Помилка читання CSV: " + e.getMessage());
            return;
        }

        double totalBalance =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        String monthYear = "01-2024";

        int count = TransactionAnalyzer.countTransactionsByMonth(
                transactions, monthYear);

        List<Transaction> topExpenses =
                TransactionAnalyzer.findTopExpenses(transactions);

        Transaction largest =
                TransactionAnalyzer.findLargestExpense(
                        transactions, monthYear);

        Transaction smallest =
                TransactionAnalyzer.findSmallestExpense(
                        transactions, monthYear);

        TransactionReportGenerator.printBalanceReport(totalBalance);

        TransactionReportGenerator.printTransactionsCountByMonth(
                monthYear, count);

        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        TransactionReportGenerator.printExpenseExtremesReport(
                monthYear, largest, smallest);

        TransactionReportGenerator.printExpensesByCategoryAndMonth(
                transactions);
    }
}
