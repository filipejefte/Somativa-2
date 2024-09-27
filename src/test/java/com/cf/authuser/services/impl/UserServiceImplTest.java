package com.cf.authuser.services.impl;

import com.cf.authuser.models.UserModel;
import com.cf.authuser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private UserModel user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new UserModel();
        user.setUserId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setUserName("Test User");
    }

    @Test
    void testFindById_UserExists() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        UserModel result = userService.findById(user.getUserId());

        assertEquals(user.getUserId(), result.getUserId());
        verify(userRepository, times(1)).findById(user.getUserId());
    }

    @Test
    void testFindById_UserNotFound() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        RuntimeException  exception = assertThrows(RuntimeException.class, () -> {
            userService.findById(UUID.randomUUID());
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testDelete_UserExists() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        userService.delete(user.getUserId());

        verify(userRepository, times(1)).delete(user);
    }
}
