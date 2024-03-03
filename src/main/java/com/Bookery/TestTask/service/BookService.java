package com.Bookery.TestTask.service;

import com.Bookery.TestTask.dto.BookDto;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages = "com.Bookery.TestTask.controller, " + "com.Bookery.TestTask.service")
public interface BookService {
    List<BookDto> findAllBooks();
}
