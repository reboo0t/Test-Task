package com.Bookery.TestTask.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class BookDto {
        private long id;
        private long isbn;
        private String title;
        private int year;
        private float price;
        private String file_name;
        private long AuthorId;
}
