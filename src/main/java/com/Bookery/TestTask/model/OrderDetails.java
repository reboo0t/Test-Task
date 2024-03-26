package com.Bookery.TestTask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    BigDecimal price;
    int quantity;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "Id=" + Id +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

/*
    @Override
    public String toString() {
        return "OrderDetails{" +
                "Id=" + Id +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
*/