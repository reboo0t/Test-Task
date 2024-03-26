package com.Bookery.TestTask.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    String status;
    LocalDate dateAdded;
    BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetails> orderDetailsList = new ArrayList<>();

    @Override
    public String toString() {
        return "Order{" +
                "id=" + Id +
                ", status='" + status + '\'' +
                ", dateAdded=" + dateAdded +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
/*
    @Override
    public String toString() {
        return "Order{" +
                "id=" + Id +
                ", status='" + status + '\'' +
                ", dateAdded=" + dateAdded +
                ", totalPrice=" + totalPrice +
                '}';
    }

 */