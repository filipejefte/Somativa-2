//package com.cf.authuser.services.impl;
//
//import com.cf.authuser.models.UserModel;
//import com.cf.authuser.repositories.UserRepository;
//import com.cf.authuser.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public List<UserModel> findAll() { return userRepository.findAll(); }
//
//    @Override
//    public Optional<UserModel> findById(UUID userId) { return userRepository.findById(userId); }
//
//    @Override
//    public void delete(UserModel userModel) { userRepository.delete(userModel); }
//
//    @Override
//    public void save(UserModel userModel) { userRepository.save(userModel); }
//}
