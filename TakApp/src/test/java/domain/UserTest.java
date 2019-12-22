/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author meriraja
 */
public class UserTest {
    
    @Test
    public void equalWhenSameUsernameandPassword() {
        User first = new User("test", "notADrill");
        User second = new User("test", "notADrill");
        assertTrue(first.equals(second));
    }
    
    @Test
    public void notEqualWhenDifferentPassword() {
        User first = new User("test", "notADrill");
        User second = new User("test", "yes");
        assertFalse(first.equals(second));
    }
}
