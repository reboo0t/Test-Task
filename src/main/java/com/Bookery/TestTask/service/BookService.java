package com.Bookery.TestTask.service;

import com.Bookery.TestTask.dto.AuthorDto;
import com.Bookery.TestTask.dto.BookDto;
import com.Bookery.TestTask.model.Author;
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

    private AuthorDto mapToAuthorDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .birthdate(author.getBirthdate())
                .build();
    }

    private Author mapToAuthor(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .birthdate(authorDto.getBirthdate())
                .build();
    }


    private BookDto mapToBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .year(book.getYear())
                .price(book.getPrice())
                .file_name(book.getFile_name())
                .author(mapToAuthorDto(book.getAuthorId()))
                .build();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public BookDto findBookById(long bookId) {
        Book book = (bookRepository.findById(bookId).get());
        return mapToBookDto(book);
    }
    public void updateBook(BookDto bookDto) {
        Book book = mapToBook(bookDto);
        bookRepository.save(book);
    }

    private Book mapToBook(BookDto book) {
        return Book.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .year(book.getYear())
                .price(book.getPrice())
                .file_name(book.getFile_name())
                .authorId(mapToAuthor(book.getAuthor()))
                .build();
    }
}
