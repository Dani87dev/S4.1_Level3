package com.dani.Task42.service;

import com.dani.Task42.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(String name, String email);
    List<User> getAllUsers();
    User getUserById(UUID id);
    List<User> getUsersByName(String name);
}