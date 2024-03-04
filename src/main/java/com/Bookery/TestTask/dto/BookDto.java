package com.Bookery.TestTask.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class BookDto {
    long id;
    long isbn;
    String title;
    int year;
    float price;
    String file_name;
    long author;
}
