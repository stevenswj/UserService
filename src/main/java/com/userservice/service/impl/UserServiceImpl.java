package com.userservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.userservice.dao.UserDAO;
import com.userservice.entity.User;
import com.userservice.dto.UserRequestBody;
import com.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize() {
        userRepository.initialize();
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public void deleteUser(String userName) {
        userRepository.deleteUser(userName);
    }

    @Override
    public void createUser(String userName, UserRequestBody request) {
        userRepository.createUser(new User(userName, request.getName(), request.getEmail()));
    }

    @Override
    public void updateUser(String userName, UserRequestBody request) {
        userRepository.updateUser(new User(userName, request.getName(), request.getEmail()));
    }
}