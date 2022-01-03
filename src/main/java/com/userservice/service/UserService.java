package com.userservice.service;

import com.userservice.entity.User;
import com.userservice.dto.UserRequestBody;

/*
 * The user service layer used for performing CRUD operations on the back-end database on behalf of the controller
 * regarding users.
 *
 * @author Weston Stevens
 */
public interface UserService {
    /*
     * Initializes the SQLite database with values, to simulate persistence from the runnable jar.
     */
    public void initialize();

    /*
     * Retrieves the user identified by the username
     *
     * @param userName - The user's username
     * @return User - The retrieved user
     */
    public User getByUserName(String userName);

    /*
     * Deletes the user identified by the username
     *
     * @param userName - The user's username
     */
    public void deleteUser(String userName);

    /*
     * Creates user with the given username and user information.
     *
     * @param userName - The new user's username
     * @param request - Information about the user in the request body
     */
    public void createUser(String userName, UserRequestBody request);

    /*
     * Updates user with the given username with new information.
     *
     * @param userName - The new user's username
     * @param request - New information about the user in the request body
     */
    public void updateUser(String userName, UserRequestBody request);
}