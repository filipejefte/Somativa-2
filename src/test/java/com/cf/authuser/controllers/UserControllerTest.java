package com.cf.authuser.controllers;
import com.cf.authuser.controllers.UserController;
import com.cf.authuser.models.UserModel;
import com.cf.authuser.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private UserModel user;

    @BeforeEach
    void setUp() {
        user = new UserModel();
        user.setUserId(UUID.fromString("17a9328f-1b73-4fea-b841-3910f0f766ec"));
        user.setEmail("test@example.com");
        user.setUserName("Test User");
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
                .andExpect(jsonPath("$").value("User not found"));
    }
}
