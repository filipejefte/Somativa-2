package com.cf.authuser.controllers;

import com.cf.authuser.dtos.UserDto;
import com.cf.authuser.models.UserModel;
import com.cf.authuser.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private UserModel user;
    private List<UserModel> userList;

    @BeforeEach
    void setUp() {
        user = new UserModel();
        user.setUserId(UUID.fromString("17a9328f-1b73-4fea-b841-3910f0f766ec"));
        user.setEmail("test@example.com");
        user.setUserName("Test User");
        user.setPhoneNumber("11987654321");

        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(user.getUserId().toString()))
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));
    }

    @Test
    void testGetOneUser_UserExists() throws Exception {
        when(userService.findById(user.getUserId())).thenReturn(user);

        mockMvc.perform(get("/users/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId().toString()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    void testGetOneUser_UserNotFound() throws Exception {
        when(userService.findById(any(UUID.class))).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(get("/users/{userId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }

    @Test
    void testDeleteUser_UserExists() throws Exception {
        mockMvc.perform(delete("/users/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        doNothing().when(userService).delete(user.getUserId());
    }

    @Test
    void testDeleteUser_UserNotFound() throws Exception {
        doThrow(new RuntimeException("User not found")).when(userService).delete(any(UUID.class));

        mockMvc.perform(delete("/users/{userId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }

    @Test
    void testUpdateUser_UserExists() throws Exception {
        when(userService.updateUser(any(UUID.class), any(UserModel.class))).thenReturn(user);

        mockMvc.perform(put("/users/{userId}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneNumber\":\"11987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(user.getPhoneNumber()));
    }

    @Test
    void testUpdateUser_UserNotFound() throws Exception {
        when(userService.updateUser(any(UUID.class), any(UserModel.class))).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(put("/users/{userId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneNumber\":\"11987654321\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
