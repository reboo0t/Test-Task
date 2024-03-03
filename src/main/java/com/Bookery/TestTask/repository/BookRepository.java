package com.Bookery.TestTask.repository;

import com.Bookery.TestTask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{
    Optional<Book> findByTitle(String url);
}
