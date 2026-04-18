package com.dani.Task42.service;

import com.dani.Task42.exceptions.UserNotFoundException;
import com.dani.Task42.models.User;
import com.dani.Task42.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_savesAndReturnsUser() {
        User user = new User(UUID.randomUUID(), "Dani", "dani@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser("Dani", "dani@example.com");

        assertNotNull(result.getId());
        assertEquals("Dani", result.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getUserById_returnsUser() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "Dani", "dani@example.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserById(id);

        assertEquals("Dani", result.getName());
    }

    @Test
    void getUserById_throwsExceptionWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(id));
    }

    @Test
    void getAllUsers_returnsList() {
        List<User> users = List.of(
                new User(UUID.randomUUID(), "Dani", "dani@example.com"),
                new User(UUID.randomUUID(), "Joan", "joan@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    void getUsersByName_returnsFilteredList() {
        List<User> users = List.of(new User(UUID.randomUUID(), "Joan", "joan@example.com"));
        when(userRepository.searchByName("jo")).thenReturn(users);

        List<User> result = userService.getUsersByName("jo");

        assertEquals(1, result.size());
        assertEquals("Joan", result.get(0).getName());
    }
}