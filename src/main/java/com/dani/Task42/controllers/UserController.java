package com.dani.Task42.controllers;

import com.dani.Task42.models.User;
import com.dani.Task42.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String name) {
        if (name == null) return service.getAllUsers();
        return service.getUsersByName(name);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable UUID id) {
        return service.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.createUser(user.getName(), user.getEmail());
    }
}