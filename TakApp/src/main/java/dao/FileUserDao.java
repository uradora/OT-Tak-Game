/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import domain.User;

/**
 * Class writes and reads usernames and passwords, implements UserDao interface.
 * @author meriraja
 */
public class FileUserDao implements UserDao {
     
    private List<User> users;
    private String file;
    
    /**
     * Read userFile and store users into an ArrayList.
     * @param file contains usernames and passwords
     * @throws Exception 
     */
    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    /**
     * Write a new user into the file
     * @throws Exception 
     */
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getName() + ";" + user.getPassword() + "\n");
            }
        } catch (Exception e) {
            return;
        }
    }
    
    /**
     * Gets the list of users.
     * @return a list of all users
     */
    @Override
    public List<User> getAll() {
        return users;
    }
    
    /**
     * Method for finding a user from the list of users.
     * @param username the name of the user to be found
     * @return null if user list is null, found user otherwise
     */
    @Override
    public User findByUsername(String username) {
        if (users != null) {
            return users.stream()
                .filter(u-> u.getName()
                .equals(username))
                .findFirst()
                .orElse(null);
        } else {
            return null;
        }
    }
    
    /**
     * Checks to see if the user has entered correct password and can login.
     * @param username name that has to match the password
     * @param password
     * @return false if user is null or an error occurs, true if password is correct
     */
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
    
    /**
     * Create a new user, add it to the list and save to file.
     * @param user object to be added
     * @return null if error, user otherwise
     * @throws Exception 
     */
    @Override
    public User createUser(User user) throws Exception {
        try {
            users.add(user);
            save();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
