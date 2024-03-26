package com.Bookery.TestTask.repository;

import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.model.Order;
import com.Bookery.TestTask.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByOrderId(long order_id);

    OrderDetails findByOrderAndBook(Order order, Book book);
}
