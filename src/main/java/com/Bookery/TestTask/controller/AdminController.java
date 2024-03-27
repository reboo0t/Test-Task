package com.Bookery.TestTask.controller;

import com.Bookery.TestTask.dto.BookDto;
import com.Bookery.TestTask.model.UserEntity;
import com.Bookery.TestTask.repository.UserRepository;
import com.Bookery.TestTask.service.UserService;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String showAllUsers(Model model) {
        List<UserEntity> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }
    @GetMapping("/admin/users/{userId}/edit")
    public String showEditUserForm(@PathVariable("userId") Long userId, Model model) {
        UserEntity user = userService.findById(userId);
        model.addAttribute("user", user);
        return "users-edit";
    }

    @PostMapping("/admin/users/{userId}edit")
    public String updateUser(@PathVariable("userId") Long userId) {
        UserEntity user = userService.findById(userId);
        user.setId(userId);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

}
