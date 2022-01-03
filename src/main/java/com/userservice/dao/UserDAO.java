package com.userservice.dao;

import com.userservice.entity.User;

/*
 * Handles the SQLite database reads/writes regarding users using Hibernate.
 *
 * @author Weston Stevens
 */
public interface UserDAO {

    /*
     * Initializes the built-in SQLite database with arbitrary initial values, to simulate an actual database.
     *
     * For a real world application, this would not need to be done.
     */
    public void initialize();

    /*
     * Inserts new user into the database if not exists.
     *
     * @param user - The constructed user object to be persisted
     * @throw BadRequestException - If user already exists.
     */
    public User getByUserName(String userName);

    /*
     * Retrieves a user from database if exists.
     *
     * @param userName - Username of the user
     * @throw BadRequestException - If user doesn't exist.
     * @return User - Information about the user.
     */
    public void deleteUser(String userName);

    /*
     * Updates information about user in the database if exists.
     *
     * @param user - User object holding updated information
     * @throw BadRequestException - If user doesn't exist.
     */
    public void createUser(User user);

    /*
     * Deletes a user from the database if exists.
     *
     * @param userName - Username of the user to be deleted.
     * @throw BadRequestException - If user doesn't exist.
     */
    public void updateUser(User user);
}