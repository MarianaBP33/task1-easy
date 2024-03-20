package com.mariana.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mariana.task1.model.User;
import com.mariana.task1.repository.UserRepository;
import com.mariana.task1.service.UserService;

public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Prepare mock data
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "user1", "user1@example.com"));
        userList.add(new User(2L, "user2", "user2@example.com"));

        // Stub the userRepository.findAll() method to return the mock data
        when(userRepository.findAll()).thenReturn(userList);

        // Call the UserService method
        List<User> result = userService.getAllUsers();

        // Verify the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("user1", result.get(0).getUsername());
        Assertions.assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    public void testGetUserById() {
        // Prepare mock data
        User user = new User(1L, "user1", "user1@example.com");

        // Stub the userRepository.findById() method to return the mock data
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the UserService method
        Optional<User> result = userService.getUserById(1L);

        // Verify the result
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("user1", result.get().getUsername());
    }

    @Test
    public void testCreateUser() {
        // Prepare mock data
        User newUser = new User(null, "newuser", "newuser@example.com");
        User savedUser = new User(1L, "newuser", "newuser@example.com");

        // Stub the userRepository.save() method to return the saved user
        when(userRepository.save(newUser)).thenReturn(savedUser);

        // Call the UserService method
        User result = userService.createUser(newUser);

        // Verify the result
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("newuser", result.getUsername());
        Assertions.assertEquals("newuser@example.com", result.getEmail());
    }

    @Test
    public void testUpdateUser() {
        // Prepare mock data
        User existingUser = new User(1L, "existinguser", "existinguser@example.com");
        User updatedUser = new User(1L, "updateduser", "updateduser@example.com");

        // Stub the userRepository.save() method to return the updated user
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Call the UserService method
        User result = userService.updateUser(existingUser);

        // Verify the result
        Assertions.assertEquals("updateduser", result.getUsername());
        Assertions.assertEquals("updateduser@example.com", result.getEmail());
    }

    @Test
    public void testDeleteUser() {
        // Call the UserService method
        userService.deleteUser(1L);

        // Verify that the userRepository.deleteById() method was called with the
        // correct argument
        verify(userRepository, times(1)).deleteById(1L);
    }

    // Write more test cases for other UserService methods...
}
