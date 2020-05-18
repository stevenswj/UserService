package com.userservice.config.db;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.userservice.service.UserService;

@Component
public class DBSetup {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize() {
        userService.initialize();
    }
}