/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import dao.UserDao;

/**
 *
 * @author meriraja
 */
public class FakeUserDao implements UserDao {
    List<User> users = new ArrayList<>();
    
    public FakeUserDao() {
        users.add(new User("test", "notADrill"));
    }
    
    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
    }
    
    @Override
    public User createUser(User user) {
        if (findByUsername(user.getName()) == null) {
            users.add(user);
            return user;
        }
        return null;
    }
    
    @Override
    public List<User> getAll() {
        return users;
    }
    
    @Override
    public Boolean correctPassword(String username, String password) {
        try {
            User user = findByUsername(username);
            
            if (user == null) {
                return false;
            }
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            return null;
        }
        return false;
    }
}
