/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author meriraja
 */
public class UserServiceTest {
    
    FakeUserDao userDao;
    UserService userservice;
    
    @Before
    public void setUp() {
        userDao =  new FakeUserDao();
        userservice = new UserService(userDao);
    }
    
    @Test
    public void userIsNotLoggedinAtStart() {
        User loggedIn = userservice.getLoggedIn();
        assertNull(loggedIn);
    }
    
    @Test
    public void createUserReturnsFalsewhenUsernameAlreadyExists() {
        boolean created = userservice.createUser("test", "testy");
        assertFalse(created);
    }
            
    @Test
    public void createdUserCanLogin() {
        userservice.createUser("anew", "user");
        boolean loggedIn = userservice.passwordCorrect("anew", "user");
        assertTrue(loggedIn);
    }
            
    @Test
    public void existingUserCanLogin() {
        boolean loggedIn = userservice.passwordCorrect("test", "notADrill");
        assertTrue(loggedIn);
    }
            
    @Test
    public void wrongPasswordCannotLogin() {
        boolean loggedIn = userservice.passwordCorrect("test", "aDrill");
        assertFalse(loggedIn);
    }
           
}
