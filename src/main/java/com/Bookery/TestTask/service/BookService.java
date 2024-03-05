package com.Bookery.TestTask.service;

import com.Bookery.TestTask.dto.BookDto;
import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    public BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book) -> mapToBookDto(book)).collect(Collectors.toList());
    }

    private BookDto mapToBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .year(book.getYear())
                .price(book.getPrice())
                .file_name(book.getFile_name())
                .build();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

}
