package com.Bookery.TestTask.dto;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class AuthorDto {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private List<Long> bookIds;
}
