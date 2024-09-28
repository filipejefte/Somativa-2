package com.cf.authuser.services.impl;

import com.cf.authuser.models.UserModel;
import com.cf.authuser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    private UserModel user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new UserModel();
        user.setUserId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setUserName("Test User");
    }

    @Test
    void testFindAll_UserExists() {
        List<UserModel> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);

        List<UserModel> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals(user.getEmail(), result.get(0).getEmail());

        verify(userRepository, times(1)).findAll();
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
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        userService.delete(user.getUserId());

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDelete_UserNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException  exception = assertThrows(RuntimeException.class, () -> {
            userService.delete(UUID.randomUUID());
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testSave_User(){
        when(userRepository.save(any())).thenReturn(user);
        userService.save((user));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdate_User(){
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        userService.updateUser(user.getUserId(), user);
        verify(userRepository, times(1)).save(user);
    }
}
