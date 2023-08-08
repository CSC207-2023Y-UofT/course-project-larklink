package entities;

import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 * This class is for testing User entity.
 */
public class UserTest {
    private Integer testUserID;
    private String testUsername;
    private String testPassword;

    @BeforeEach
    public void setUp(){
        testUserID = 55;
        testUsername = "larkLink";
        testPassword = "myPassword";
    }

    /**
     * Tests setUser method.
     */
    @Test
    public void testSetUser() {
        User.setUser(testUserID, testUsername, testPassword);
        assert User.getUserID().equals(testUserID);
        assert User.getUsername().equals(testUsername);
        assert BCrypt.checkpw(testPassword, User.getPassword());
    }

    /**
     * Tests getUserID method.
     */
    @Test
    public void testGetUserID() {
        User.setUser(testUserID, testUsername, testPassword);
        // checks that expected value is equal to actual value from getUserID
        assert User.getUserID().equals(testUserID);
    }

    /**
     * Tests getUsername method.
     */
    @Test
    public void testGetUsername() {
        User.setUser(testUserID, testUsername, testPassword);
        // checks that expected value is equal to actual value from getUsername
        assert User.getUsername().equals(testUsername);
    }

    /**
     * Tests getPassword method.
     */
    @Test
    public void testGetPassword() {
        User.setUser(testUserID, testUsername, testPassword);
        assert BCrypt.checkpw(testPassword, User.getPassword());
    }
}
