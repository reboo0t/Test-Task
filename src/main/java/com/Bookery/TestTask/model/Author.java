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
    long id;
    String name;
    LocalDate birthdate;

    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL)
    List<Book> books;
}