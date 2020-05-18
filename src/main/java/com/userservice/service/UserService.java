package com.userservice.service;

import com.userservice.entity.User;
import com.userservice.dto.UserRequestBody;

public interface UserService {
    public void initialize();
    public User getByUserName(String userName);
    public void deleteUser(String userName);
    public void createUser(String userName, UserRequestBody request);
    public void updateUser(String userName, UserRequestBody request);
}