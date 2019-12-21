/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.UserDao;

/**
 *
 * @author meriraja
 */
public class UserService {
    
    private UserDao userDao;
    private User loggedIn;
    
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public Boolean createUser(String name, String password) {
        if (userDao.findByUsername(name) != null) {
            return false;
        }

        User user = new User(name, password);
        
        try {
            userDao.createUser(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
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
