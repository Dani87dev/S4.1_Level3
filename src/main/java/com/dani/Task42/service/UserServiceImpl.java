package com.dani.Task42.service;

import com.dani.Task42.exceptions.UserNotFoundException;
import com.dani.Task42.models.User;
import com.dani.Task42.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User(UUID.randomUUID(), name, email);
        return repo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return repo.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return repo.searchByName(name);
    }
}