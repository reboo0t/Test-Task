package com.Bookery.TestTask.dto;

import com.Bookery.TestTask.model.OrderDetails;
import com.Bookery.TestTask.model.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class OrderDto {
    long Id;
    String status;
    LocalDate dateAdded;
    BigDecimal totalPrice;
    UserEntity user_id;
    OrderDetails orderDetailsList;
}
