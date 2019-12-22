/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * Class for creating User objects.
 * @author meriraja
 */
public class User {
    
    private String name;
    private String password;
    
    /**
     * Constructor for user objects.
     * @param name given username
     * @param password given password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return ((name.equals(other.name)) && (password.equals(other.password)));
    }
}
