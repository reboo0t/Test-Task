package com.Bookery.TestTask.service;

import com.Bookery.TestTask.dto.OrderDetailsDto;
import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.model.Order;
import com.Bookery.TestTask.model.OrderDetails;
import com.Bookery.TestTask.repository.OrderDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailsService {

    public OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    private OrderDetails mapToOrderDetails(OrderDetailsDto orderDetails) {
        return OrderDetails.builder()
                .Id(orderDetails.getId())
                .price(orderDetails.getPrice())
                .quantity(orderDetails.getQuantity())
                .book(orderDetails.getBook())
                .order(orderDetails.getOrder_id())
                .build();
    }

    private OrderDetailsDto mapToOrderDetailsDto(OrderDetails orderDetails) {
        return OrderDetailsDto.builder()
                .Id(orderDetails.getId())
                .price(orderDetails.getPrice())
                .quantity(orderDetails.getQuantity())
                .book(orderDetails.getBook())
                .order_id(orderDetails.getOrder())
                .build();
    }

    public List<OrderDetailsDto> findAllOrderDetailsByOrderId(Order orderId) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAllByOrderId(orderId.getId());
        return orderDetails.stream().map((orderDetail) -> mapToOrderDetailsDto(orderDetail)).collect(Collectors.toList());
    }


    public OrderDetailsDto findOrderDetailsById(long orderDetailsId) {
        OrderDetails orderDetails = (orderDetailsRepository.findById(orderDetailsId).get());
        return mapToOrderDetailsDto(orderDetails);
    }

    public void saveOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public void updateOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = mapToOrderDetails(orderDetailsDto);
        orderDetailsRepository.save(orderDetails);
    }

    public void deleteOrderDetailsById(long id) {
        orderDetailsRepository.deleteById(id);
    }

    public OrderDetails findOrderDetailsByOrderAndBook(Order order, Book book) {
        return orderDetailsRepository.findByOrderAndBook(order, book);
    }

}
