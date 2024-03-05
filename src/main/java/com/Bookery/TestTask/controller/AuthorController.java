package com.Bookery.TestTask.controller;

import com.Bookery.TestTask.dto.AuthorDto;
import com.Bookery.TestTask.model.Author;
import com.Bookery.TestTask.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listBooks(Model model) {
        List<AuthorDto> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "authors-list";
    }

    @GetMapping("/authors/new")
    public String createAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "authors-create";
    }

    @PostMapping("/authors/new")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }
}
