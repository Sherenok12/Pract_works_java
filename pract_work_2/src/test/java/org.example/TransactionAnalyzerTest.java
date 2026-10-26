package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCountTransactionsByMonth() {

        Transaction transaction1 =
                new Transaction("01-02-2023", 50.0, "Дохід");

        Transaction transaction2 =
                new Transaction("15-02-2023", -20.0, "Витрата");

        Transaction transaction3 =
                new Transaction("05-03-2023", 100.0, "Дохід");

        List<Transaction> transactions = Arrays.asList(
                transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(
                transactions, "02-2023");

        int countMar = TransactionAnalyzer.countTransactionsByMonth(
                transactions, "03-2023");

        Assertions.assertEquals(
                2, countFeb,
                "Кількість транзакцій за лютий неправильна");

        Assertions.assertEquals(
                1, countMar,
                "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testCalculateTotalBalance() {

        Transaction transaction1 =
                new Transaction("01-02-2023", 100.0, "Дохід");

        Transaction transaction2 =
                new Transaction("15-02-2023", -50.0, "Витрата");

        Transaction transaction3 =
                new Transaction("05-03-2023", 150.0, "Дохід");

        List<Transaction> transactions = Arrays.asList(
                transaction1, transaction2, transaction3);

        double result =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(
                200.0, result,
                "Розрахунок загального балансу неправильний");
    }
    @Test
    public void testFindTopExpenses() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-02-2023", -100.0, "Витрата 1"),
                new Transaction("02-02-2023", -500.0, "Витрата 2"),
                new Transaction("03-02-2023", -50.0, "Витрата 3"),
                new Transaction("04-02-2023", 200.0, "Дохід")
        );

        List<Transaction> result =
                TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(-500.0, result.get(0).getAmount());
        Assertions.assertEquals(-100.0, result.get(1).getAmount());
        Assertions.assertEquals(-50.0, result.get(2).getAmount());
    }

    @Test
    public void testFindLargestAndSmallestExpense() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-02-2023", -100.0, "Їжа"),
                new Transaction("05-02-2023", -500.0, "Оренда"),
                new Transaction("10-02-2023", -50.0, "Транспорт"),
                new Transaction("15-03-2023", -700.0, "Інша витрата")
        );

        Transaction largest =
                TransactionAnalyzer.findLargestExpense(
                        transactions, "02-2023");

        Transaction smallest =
                TransactionAnalyzer.findSmallestExpense(
                        transactions, "02-2023");

        Assertions.assertNotNull(largest);
        Assertions.assertNotNull(smallest);

        Assertions.assertEquals(-500.0, largest.getAmount());

        Assertions.assertEquals(-50.0, smallest.getAmount());
    }

    @Test
    public void testNoExpensesInMonth() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-02-2023", 100.0, "Дохід")
        );

        Transaction largest =
                TransactionAnalyzer.findLargestExpense(
                        transactions, "02-2023");

        Transaction smallest =
                TransactionAnalyzer.findSmallestExpense(
                        transactions, "02-2023");

        Assertions.assertNull(largest);
        Assertions.assertNull(smallest);
    }

}
