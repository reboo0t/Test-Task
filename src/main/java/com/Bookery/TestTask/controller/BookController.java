package com.Bookery.TestTask.controller;


import com.Bookery.TestTask.dto.AuthorDto;
import com.Bookery.TestTask.dto.BookDto;
import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.service.AuthorService;
import com.Bookery.TestTask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BookController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/covers";
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping({"/books", "/"})
    public String listBooks(Model model) {
        List<BookDto> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/books/new")
    public String createBookForm(Model model) {
        Book book = new Book();
        List<AuthorDto> authors = authorService.findAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "books-create";
    }

    @PostMapping("/books/new")
    public String saveBook(@ModelAttribute("book") Book book, @RequestParam("image") MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        book.setFile_name(file.getOriginalFilename());
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{bookId}/edit")
    public String editBookForm(@PathVariable("bookId") long bookId, Model model) {
        BookDto book = bookService.findBookById(bookId);
        List<AuthorDto> authors = authorService.findAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "books-edit";
    }

    @PostMapping("/books/{bookId}/edit")
    public String updateBook(@PathVariable("bookId") Long bookId, @ModelAttribute("book") BookDto book,  @RequestParam("image") MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        book.setId(bookId);
        bookService.updateBook(book);
        return "redirect:/books";
    }
}
