package com.dani.Task42.controllers;

import com.dani.Task42.exceptions.UserNameNotFoundException;
import com.dani.Task42.exceptions.UserNotFoundException;
import com.dani.Task42.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    static List<User> usersList = new ArrayList<>();

    /*
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return usersList;
    }
    */

    @GetMapping("/users")
    public List<User> getUserByName(@RequestParam(required = false) String name) {

        List<User> listUsersWithName = new ArrayList<>();

        if (name == null) return usersList;

        for (User user : usersList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                listUsersWithName.add(user);
            }
        }
        return listUsersWithName;

    }

    @GetMapping("/users/{id}")
    public User getEspecificUser(@PathVariable UUID id) {
        User user1;
        for (User user : usersList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        UUID randomId = UUID.randomUUID();
        String nameUser = user.getName();
        String emailUser = user.getEmail();
        User user1 = new User(randomId, nameUser, emailUser);

        usersList.add(user1);
        return user1;
    }

}
