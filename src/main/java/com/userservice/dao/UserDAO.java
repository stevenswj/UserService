package com.userservice.dao;

import com.userservice.entity.User;

public interface UserDAO {
    public void initialize();
    public User getByUserName(String userName);
    public void deleteUser(String userName);
    public void createUser(User user);
    public void updateUser(User user);
}