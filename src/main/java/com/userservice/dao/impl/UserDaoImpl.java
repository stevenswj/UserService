package com.userservice.dao.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userservice.dao.UserDAO;
import com.userservice.entity.User;
import com.userservice.error.BadRequestException;

/*
 * Handles the SQLite database reads/writes regarding users using Hibernate.
 *
 * @author Weston Stevens
 */
@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDAO {

    /*
     * Session bean
     */
    @Autowired
    private SessionFactory sessionFactory;

    /*
     * Initializes the built-in SQLite database with arbitrary initial values, to simulate an actual database.
     *
     * For a real world application, this would not need to be done.
     */
    @Override
    public void initialize() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<User> users = new ArrayList<User>();
        users.add(new User("stevenswj", "Weston Stevens", "weston.stevens3@gmail.com"));
        users.add(new User("jdoe", "John Doe", "john.doe@gmail.com"));

        User current;
        for(int i = 0; i < users.size(); i++) {
            current = users.get(i);
            session.save(current);
            if( i % 50 == 0 ) { // 50, same as the JDBC batch size
                // Flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }

        tx.commit();
        session.close();
    }

    /*
     * Inserts new user into the database if not exists.
     *
     * @param user - The constructed user object to be persisted
     * @throw BadRequestException - If user already exists.
     */
    @Override
    public void createUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        try {
            tx.commit();
        } catch(PersistenceException e) { // Duplicate username
            tx.rollback();
            throw new BadRequestException("Duplicate username.");
        } finally {
            session.close();
        }
    }

    /*
     * Retrieves a user from database if exists.
     *
     * @param userName - Username of the user
     * @throw BadRequestException - If user doesn't exist.
     * @return User - Information about the user.
     */
    @Override
    public User getByUserName(String userName) {
        Session session = sessionFactory.openSession();
        String hql = "FROM User WHERE userName = '" + userName + "'";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        session.close();
        try {
            return users.get(0);
        } catch(IndexOutOfBoundsException e) { // Username not found
            throw new BadRequestException("Username not found.");
        }
    }

    /*
     * Updates information about user in the database if exists.
     *
     * @param user - User object holding updated information
     * @throw BadRequestException - If user doesn't exist.
     */
    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "UPDATE User SET name = :name, " +
            "email = :email " +
            "WHERE userName = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("userName", user.getUserName());
        try {
            if (query.executeUpdate() == 0) {
                throw new BadRequestException("Username not found.");
            } else {
                tx.commit();
            }
        } catch(BadRequestException e) { // Username not found
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
     * Deletes a user from the database if exists.
     *
     * @param userName - Username of the user to be deleted.
     * @throw BadRequestException - If user doesn't exist.
     */
    @Override
    public void deleteUser(String userName) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "DELETE FROM User WHERE userName= :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);

        try {
            if (query.executeUpdate() == 0) { // Username not found
                throw new BadRequestException("Username not found.");
            } else {
                tx.commit();
            }
        } catch(BadRequestException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}