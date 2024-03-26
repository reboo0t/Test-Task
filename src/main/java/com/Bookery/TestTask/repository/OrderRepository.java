package com.Bookery.TestTask.repository;

import com.Bookery.TestTask.model.Order;
import com.Bookery.TestTask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByStatus(String status);

    Order findByUserAndStatus(UserEntity user, String status);

    Order findByUser(UserEntity user);
}
