/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.UserDao;

/**
 * Provides methods for interacting with Dao classes.
 * @author meriraja
 */
public class UserService {
    
    private UserDao userDao;
    private User loggedIn;
    
    /**
     * Initialize userDao class.
     * @param userDao 
     */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    /**
     * Method for creating a new user.
     * @param name username given
     * @param password password given
     * @return true if user correctly created, false otherwise
     */
    public Boolean createUser(String name, String password) {
        if (userDao.findByUsername(name) != null) {
            return false;
        }

        User user = new User(name, password);
                
        try {
            userDao.createUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if user password matches username.
     * @param username username given
     * @param password password given
     * @return true if password is correct, false otherwise
     */
    public Boolean passwordCorrect(String username, String password) {
        if (userDao.correctPassword(username, password)) {
            loggedIn = userDao.findByUsername(username);
            return true;
        }
        return false;
    }
    
    public User getLoggedIn() {
        return loggedIn;
    }
    
}
