package org.example;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {

    private List<Product> products; // Список товарів у замовленні
    private double totalPrice;      // Загальна вартість замовлення

    @Setter
    private String status;          // Статус замовлення

    // Конструктор
    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
    }

    // Метод для виведення інформації про замовлення
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Замовлення:\n");

        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }

        sb.append("Загальна вартість: ")
                .append(totalPrice)
                .append("\n");

        sb.append("Статус: ")
                .append(status);

        return sb.toString();
    }
}