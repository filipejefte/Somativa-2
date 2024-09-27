package com.cf.authuser.services;

import com.cf.authuser.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserModel> findAll();

    UserModel findById(UUID userId);

    void delete(UUID userId);

    void save(UserModel userModel);

    UserModel updateUser(UUID userId, UserModel userModel);
}
