package com.Bookery.TestTask.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    long id;
    long isbn;
    String title;
    int year;
    float price;
    String file_name;
    AuthorDto author;
}
