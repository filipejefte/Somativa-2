//package com.cf.authuser.controllers;
//
//import com.cf.authuser.dtos.UserDto;
//import com.cf.authuser.models.UserModel;
//import com.cf.authuser.services.impl.UserServiceImpl;
//import com.fasterxml.jackson.annotation.JsonView;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    UserServiceImpl userService;
//
//    @GetMapping
//    public ResponseEntity<List<UserModel>> getAllUsers(){
//        List<UserModel> userModel = userService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(userModel);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<Object> getOneUser (@PathVariable(value = "userId") UUID userId){
//        Optional<UserModel> userModelOptional = userService.findById(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<Object> deleteUser (@PathVariable(value = "userId") UUID userId){
//        Optional<UserModel> userModelOptional = userService.findById(userId);
//        userService.delete(userModelOptional.get());
//        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
//    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
//                                             @RequestBody UserDto userDto){
//        Optional<UserModel> userModelOptional = userService.findById(userId);
//
//        var userModel = userModelOptional.get();
//        userModel.setPhoneNumber(userDto.getPhoneNumber());
//        userModel.setCpf(userDto.getCpf());
//        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
//        userService.save(userModel);
//        return ResponseEntity.status(HttpStatus.OK).body(userModel);
//
//    }
//}
