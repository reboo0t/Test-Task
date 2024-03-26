package com.Bookery.TestTask.service;

import com.Bookery.TestTask.dto.OrderDto;
import com.Bookery.TestTask.model.Order;
import com.Bookery.TestTask.model.UserEntity;
import com.Bookery.TestTask.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    public OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map((order) -> mapToOrderDto(order)).collect(Collectors.toList());
    }

    private Order mapToOrder(OrderDto order) {
        return Order.builder()
                .Id(order.getId())
                .dateAdded(order.getDateAdded())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .user(order.getUser_id())
                .build();
    }

    private OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .Id(order.getId())
                .dateAdded(order.getDateAdded())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .user_id(order.getUser())
                .build();
    }

    public Order findOrderByUserAndStatus(UserEntity user, String status) {
        return orderRepository.findByUserAndStatus(user, status);
    }

    public BigDecimal calculateTotalPrice(Order order) {
        if (order.getOrderDetailsList() == null) {
            return BigDecimal.ZERO;
        } else {
            return order.getOrderDetailsList().stream()
                    .map(orderDetails -> orderDetails.getPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    public OrderDto findOrderById(long orderId) {
        Order order = (orderRepository.findById(orderId).get());
        return mapToOrderDto(order);
    }

    public OrderDto findOrderByStatus(String status) {
        Order order = orderRepository.findByStatus(status);
        return mapToOrderDto(order);
    }

    public OrderDto findOrderByUser(UserEntity user) {
        Order order = orderRepository.findByUser(user);
        return mapToOrderDto(order);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void updateOrder(OrderDto orderDto) {
        Order order = mapToOrder(orderDto);
        orderRepository.save(order);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

}
