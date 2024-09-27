package com.cf.authuser.controllers;

import com.cf.authuser.dtos.UserDto;
import com.cf.authuser.models.UserModel;
import com.cf.authuser.services.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModel = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser (@PathVariable(value = "userId") UUID userId){
        try{
            UserModel userModel = userService.findById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser (@PathVariable(value = "userId") UUID userId){
        try{
            userService.delete(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully.");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody UserDto userDto) {
        try {
            UserModel updatedUser = userService.updateUser(userId, new UserModel(userDto));
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
