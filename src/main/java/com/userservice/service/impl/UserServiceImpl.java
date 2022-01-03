package com.userservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.userservice.dao.UserDAO;
import com.userservice.entity.User;
import com.userservice.dto.UserRequestBody;
import com.userservice.service.UserService;

/*
 * The user service layer used for performing CRUD operations on the back-end database on behalf of the controller
 * regarding users.
 *
 * @author Weston Stevens
 */
@Service
public class UserServiceImpl implements UserService {

    /*
     * DAO object used by this service
     */
    private final UserDAO userRepository;

    /*
     * Constructor
     *
     * @param userRepository - DAO object used by this service
     */
    @Autowired
    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * Initializes the SQLite database with values, to simulate persistence from the runnable jar.
     */
    @Override
    public void initialize() {
        userRepository.initialize();
    }

    /*
     * Retrieves the user identified by the username
     *
     * @param userName - The user's username
     * @return User - The retrieved user
     */
    @Override
    public User getByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    /*
     * Deletes the user identified by the username
     *
     * @param userName - The user's username
     */
    @Override
    public void deleteUser(String userName) {
        userRepository.deleteUser(userName);
    }

    /*
     * Creates user with the given username and user information.
     *
     * @param userName - The new user's username
     * @param request - Information about the user in the request body
     */
    @Override
    public void createUser(String userName, UserRequestBody request) {
        userRepository.createUser(new User(userName, request.getName(), request.getEmail()));
    }

    /*
     * Updates user with the given username with new information.
     *
     * @param userName - The new user's username
     * @param request - New information about the user in the request body
     */
    @Override
    public void updateUser(String userName, UserRequestBody request) {
        userRepository.updateUser(new User(userName, request.getName(), request.getEmail()));
    }
}