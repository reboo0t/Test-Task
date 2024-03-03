package com.Bookery.TestTask.service.impl;

import com.Bookery.TestTask.dto.BookDto;
import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.repository.BookRepository;
import com.Bookery.TestTask.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    public BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book) -> mapToBookDto(book)).collect(Collectors.toList());
    }

    private BookDto mapToBookDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .year(book.getYear())
                .price(book.getPrice())
                .file_name(book.getFile_name())
                .build();
    }

}
