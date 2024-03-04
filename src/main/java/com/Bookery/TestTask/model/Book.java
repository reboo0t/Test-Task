package com.Bookery.TestTask.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "books")
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
}
