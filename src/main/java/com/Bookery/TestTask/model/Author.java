package com.Bookery.TestTask.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "AuthorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;
}