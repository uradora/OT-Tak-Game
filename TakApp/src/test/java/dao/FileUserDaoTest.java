/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import domain.User;
/**
 *
 * @author meriraja
 */
public class FileUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;
    UserDao dao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("test;notADrill\n");
        }
        
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
    
    @Test
    public void testUserIsReadCorrectly() {
        List<User> users = dao.getAll();
        assertEquals("test", users.get(0).getName());
        assertEquals("notADrill", users.get(0).getPassword());
    }
    
    @Test
    public void testUserIsFound() {
        assertEquals("test", dao.findByUsername("test").getName());
        assertEquals("notADrill", dao.findByUsername("test").getPassword());
    }
    
    @Test
    public void testUserCanLogIn() {
        boolean loggedIn = dao.correctPassword("test", "notADrill");
        assertTrue(loggedIn);
    }
    
    @Test
    public void nonExistingUserIsNotFound() {
        assertEquals(null, dao.findByUsername("meriraja"));
    }
    
    @Test
    public void newUserCanBeSavedAndFound() {
        try {
            User meri = dao.createUser(new User("meriraja", "meriraja"));
        } catch (Exception e) {
            return;
        }
        assertEquals("meriraja", dao.findByUsername("meriraja").getName());
        assertEquals("meriraja", dao.findByUsername("meriraja").getPassword());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
}
