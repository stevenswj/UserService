package com.userservice.config.db;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.userservice.service.UserService;

/*
 * One of the first things run by the application is to initialize the SQLite database with arbitrary values.
 *
 * @author Weston Stevens
 */
@Component
public class DBSetup {
    @Autowired
    private UserService userService;

    // Initializes the built-in SQLite database with arbitrary initial values, to simulate an actual persisted database
    @PostConstruct
    public void initialize() {
        userService.initialize();
    }
}