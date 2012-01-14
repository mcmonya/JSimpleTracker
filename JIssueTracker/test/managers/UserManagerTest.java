/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import exceptions.UserNotFoundException;
import config.TestConnectionProvider;
import entities.User;
import exceptions.UserCreationException;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mychal
 */
public class UserManagerTest {
    
    private UserManager userManager = new UserManager(new TestConnectionProvider());
    
    public UserManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.sqlite.JDBC");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws SQLException{
        UserManager.dropTableIfExists(new TestConnectionProvider());
        UserManager.createTableIfNotExists(new TestConnectionProvider());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateUser() throws UserCreationException{
        User user = userManager.createUser("login", "hasło");
        assertTrue(user.getId() != -1);
    }

    @Test(expected=UserCreationException.class)
    public void testDontAllowDuplicateLogin() throws UserCreationException
    {
        userManager.createUser("login", "hasło");
        userManager.createUser("login", "blabla");
    }
    
    @Test
    public void testGetAllUsers() throws SQLException, UserCreationException{
        userManager.createUser("user1", "pass1");
        userManager.createUser("user2", "pass2");
        List<User> res = userManager.getAllUsers();
        assertTrue(res.size() == 2);
    }

    @Test
    public void testGetUserById() throws UserCreationException, SQLException, 
            UserCreationException, UserNotFoundException{
        User user = userManager.createUser("user1", "pass1");
        User retrieved = userManager.getUserById(user.getId());
        assertEquals(user, retrieved);
    }

    @Test
    public void testRemoveUser() throws SQLException, UserCreationException{
        User user = userManager.createUser("user", "pass");
        userManager.removeUser(user);
        List<User> allUsers = userManager.getAllUsers();
        assertTrue(allUsers.isEmpty());
    }
    
    @Test(expected=UserCreationException.class)
    public void testWrongLoginAndPassword() throws UserCreationException
    {
        userManager.createUser(" ", "");
    }
    
    @Test
    public void testTrimmingLogin() throws UserCreationException
    {
        User user = userManager.createUser("   login  ", "pass");
        assertEquals("login", user.getLogin());
    }
}
