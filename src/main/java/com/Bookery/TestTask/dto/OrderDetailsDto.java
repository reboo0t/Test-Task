package com.Bookery.TestTask.dto;

import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.model.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDetailsDto {
    long Id;
    Book book;
    BigDecimal price;
    int quantity;
    Order order_id;
}
