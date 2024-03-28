package com.Bookery.TestTask.controller;


import com.Bookery.TestTask.dto.OrderDetailsDto;
import com.Bookery.TestTask.dto.OrderDto;
import com.Bookery.TestTask.model.Book;
import com.Bookery.TestTask.model.Order;
import com.Bookery.TestTask.model.OrderDetails;
import com.Bookery.TestTask.model.UserEntity;
import com.Bookery.TestTask.repository.BookRepository;
import com.Bookery.TestTask.service.OrderDetailsService;
import com.Bookery.TestTask.service.OrderService;
import com.Bookery.TestTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CartController {
    private final BookRepository bookRepository;
    private final OrderDetailsService orderDetailsService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public CartController(BookRepository bookRepository, OrderDetailsService orderDetailsService, UserService userService, OrderService orderService) {
        this.bookRepository = bookRepository;
        this.orderDetailsService = orderDetailsService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String listOrders(Model model, Principal principal) {
        UserEntity currentUser = userService.findByEmail(principal.getName());
        List<OrderDto> orders = orderService.findAllOrdersByUser(currentUser);
        model.addAttribute("orders", orders);
        return "orders-list";
    }

    @GetMapping("/cart/{orderId}")
    public String listOrderDetails(Model model, @PathVariable Order orderId) {
        List<OrderDetailsDto> orderDetails = orderDetailsService.findAllOrderDetailsByOrderId(orderId);
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }

    @PostMapping("/cart/add/{bookId}")
    public String addToCart(@PathVariable("bookId") Long bookId, Principal principal) {
        UserEntity currentUser = userService.findByEmail(principal.getName());
        Order order;
        try {
            order = orderService.findOrderByUserAndStatus(currentUser, "In Progress");
            if (order == null) {
                order = new Order();
                order.setUser(currentUser);
                order.setStatus("In Progress");
                order.setDateAdded(LocalDate.now());
                order = orderService.saveOrder(order);
            }
        } catch (DataIntegrityViolationException e) {
            return "redirect:/error";
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        OrderDetails existingOrderDetails = orderDetailsService.findOrderDetailsByOrderAndBook(order, book);
        if (existingOrderDetails != null) {
            existingOrderDetails.setQuantity(existingOrderDetails.getQuantity() + 1);
            orderDetailsService.saveOrderDetails(existingOrderDetails);
        } else {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setBook(book);
            orderDetails.setPrice(BigDecimal.valueOf(book.getPrice()));
            orderDetails.setQuantity(1);
            orderDetails.setOrder(order);
            orderDetailsService.saveOrderDetails(orderDetails);
        }

        BigDecimal totalPrice = orderService.calculateTotalPrice(order);
        if (order.getTotalPrice() == null) {
            totalPrice = BigDecimal.valueOf(book.getPrice());
        }
        order.setTotalPrice(totalPrice);
        orderService.saveOrder(order);

        return "redirect:/books";
    }

    @GetMapping("/cart/{orderId}/delete")
    public String deleteOrder(@PathVariable("orderId") long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/details/{detailId}/delete")
    public String deleteOrderDetail(@PathVariable("detailId") long detailId) {
        orderDetailsService.deleteOrderDetailsById(detailId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/admin")
    public String manageOrder(Model model) {
        List<OrderDto> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "orders-manage";
    }

    @GetMapping("/cart/admin/{orderId}/complete")
    public String completeOrder(@PathVariable("orderId") long orderId) {
        orderService.updateOrderStatus(orderId, "Completed");
        return "redirect:/cart/admin";
    }

    @GetMapping("/cart/{orderId}/pend")
    public String pendOrder(@PathVariable("orderId") long orderId) {
        orderService.updateOrderStatus(orderId, "Pending");
        return "redirect:/cart";
    }

}
