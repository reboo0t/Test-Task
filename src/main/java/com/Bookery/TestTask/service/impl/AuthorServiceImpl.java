package com.Bookery.TestTask.service.impl;

import com.Bookery.TestTask.dto.AuthorDto;
import com.Bookery.TestTask.model.Author;
import com.Bookery.TestTask.repository.AuthorRepository;
import com.Bookery.TestTask.repository.BookRepository;
import com.Bookery.TestTask.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    public AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map((author) -> mapToAuthorDto(author)).collect(Collectors.toList());
    }

    private AuthorDto mapToAuthorDto(Author author){
        return AuthorDto.builder()
                .id(author.getId())
                .birthdate(author.getBirthdate())
                .name(author.getName())
                .build();
    }

}
