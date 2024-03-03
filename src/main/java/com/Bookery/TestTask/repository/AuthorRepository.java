package com.Bookery.TestTask.repository;

import com.Bookery.TestTask.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String url);
}
