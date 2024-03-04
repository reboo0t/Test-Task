package com.Bookery.TestTask.service;

import com.Bookery.TestTask.dto.AuthorDto;
import com.Bookery.TestTask.model.Author;
import com.Bookery.TestTask.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    public AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) { this.authorRepository = authorRepository; }

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
