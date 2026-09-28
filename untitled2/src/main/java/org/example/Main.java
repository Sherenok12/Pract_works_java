package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення товарів
        Product product1 = new Product(
                1,
                "Ноутбук",
                19999.99,
                "Високопродуктивний ноутбук для роботи та ігор",
                electronics
        );

        Product product2 = new Product(
                2,
                "Смартфон",
                12999.50,
                "Смартфон з великим екраном",
                smartphones
        );

        Product product3 = new Product(
                3,
                "Навушники",
                2499.00,
                "Бездротові навушники з шумозаглушенням",
                accessories
        );

        // Створення кошика
        Cart cart = new Cart();

        // Історія замовлень
        List<Order> orderHistory = new ArrayList<>();

        while (true) {

            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Історія замовлень");
            System.out.println("7 - Пошук товару");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");

                    int id = scanner.nextInt();

                    if (id == 1) {
                        cart.addProduct(product1);
                    } else if (id == 2) {
                        cart.addProduct(product2);
                    } else if (id == 3) {
                        cart.addProduct(product3);
                    } else {
                        System.out.println("Товар з таким ID не знайдено");
                    }

                    break;

                case 3:
                    System.out.println(cart);
                    break;

                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);

                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);

                        cart.clear();
                    }

                    break;

                case 5:
                    System.out.println("Введіть ID товару для видалення:");

                    int removeId = scanner.nextInt();

                    if (removeId == 1) {
                        cart.removeProduct(product1);
                    } else if (removeId == 2) {
                        cart.removeProduct(product2);
                    } else if (removeId == 3) {
                        cart.removeProduct(product3);
                    } else {
                        System.out.println("Товар з таким ID не знайдено");
                    }

                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("Історія замовлень:");

                        for (Order order : orderHistory) {
                            System.out.println(order);
                            System.out.println("----------------");
                        }
                    }

                    break;

                case 7:
                    scanner.nextLine();

                    System.out.println("Введіть назву товару або категорію:");

                    String search = scanner.nextLine().toLowerCase();

                    if (product1.getName().toLowerCase().contains(search)
                            || product1.getCategory().getName().toLowerCase().contains(search)) {
                        System.out.println(product1);
                    }

                    if (product2.getName().toLowerCase().contains(search)
                            || product2.getCategory().getName().toLowerCase().contains(search)) {
                        System.out.println(product2);
                    }

                    if (product3.getName().toLowerCase().contains(search)
                            || product3.getCategory().getName().toLowerCase().contains(search)) {
                        System.out.println(product3);
                    }

                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}