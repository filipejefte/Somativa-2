package com.cf.authuser.services.impl;

import com.cf.authuser.models.UserModel;
import com.cf.authuser.repositories.UserRepository;
import com.cf.authuser.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void delete(UUID userId) {
        UserModel userModel = findById(userId);
        userRepository.delete(userModel);
    }

    @Override
    public void save(UserModel userModel) { userRepository.save(userModel); }

    @Override
    public UserModel updateUser(UUID userId, UserModel userModelData) {
        UserModel userModel = findById(userId);
        userModel.setPhoneNumber(userModelData.getPhoneNumber());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        save(userModel);
        return userModel;
    }
}
