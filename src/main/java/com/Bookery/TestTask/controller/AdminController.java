package com.Bookery.TestTask.controller;

import com.Bookery.TestTask.model.UserEntity;
import com.Bookery.TestTask.repository.UserRepository;
import com.Bookery.TestTask.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @PostMapping("/admin/users/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long userId, @ModelAttribute("user") UserEntity updatedUser) {
        UserEntity user = userService.findById(userId);
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{userId}/delete")
    public String deleteBook(@PathVariable("userId") long userId) {
        userService.deleteById(userId);
        return "redirect:/admin/users";
    }


}
