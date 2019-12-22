/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import domain.User;
/**
 * @author meriraja
 * Interface for Dao classes
 */
public interface UserDao {
    
    User findByUsername(String username);
    
    User createUser(User user) throws Exception;
    
    Boolean correctPassword(String username, String password);
    
    List<User> getAll();
    
}
