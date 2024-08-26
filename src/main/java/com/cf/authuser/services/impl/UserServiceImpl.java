package com.cf.authuser.services.impl;

import com.cf.authuser.models.UserModel;
import com.cf.authuser.repositories.UserRepository;
import com.cf.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(UserModel userModel) { userRepository.save(userModel); }
}
