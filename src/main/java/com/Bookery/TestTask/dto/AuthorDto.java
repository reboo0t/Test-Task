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
    @Override
    public String toString() {
        return String.valueOf(id);
    }

}