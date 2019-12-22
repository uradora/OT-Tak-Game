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
 *
 * @author meriraja
 */
public class FileUserDao implements UserDao {
     
    private List<User> users;
    private String file;
    
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
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getName() + ";" + user.getPassword() + "\n");
            }
        } catch (Exception e) {
            return;
        }
    }
    
    @Override
    public List<User> getAll() {
        return users;
    }
    
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
