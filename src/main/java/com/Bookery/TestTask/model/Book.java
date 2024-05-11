package com.Bookery.TestTask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long isbn;
    String title;
    int year;
    float price;
    String file_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    Author authorId;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    Collection<OrderDetails> orderDetailsList = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", file_name=" + file_name +
                '}';
    }
}
/*
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", file_name=" + file_name +
                '}';
    }

 */