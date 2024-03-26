package com.Bookery.TestTask.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class AuthorDto {
    Long id;
    String name;
    LocalDate birthdate;
    List<Long> bookIds;
}