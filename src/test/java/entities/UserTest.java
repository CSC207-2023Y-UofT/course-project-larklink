package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
     * Tests setUser method and getters
     */
    @Test
    public void testSetUser() {
        User.setUser(testUserID, testUsername, testPassword);

        // checks that the values are set correctly by comparing expected and actual values
        assertEquals(testUserID, User.getUserID());
        assertEquals(testUsername, User.getUsername());
        assertTrue(User.checkPassword(testPassword, User.getPassword()));
    }

    /**
     * Tests getUserID method.
     */
    @Test
    public void testGetUserID() {
        User.setUser(testUserID, testUsername, testPassword);
        // checks that expected value is equal to actual value from getUserID
        assertEquals(testUserID, User.getUserID());
    }

    /**
     * Tests getUsername method.
     */
    @Test
    public void testGetUsername() {
        User.setUser(testUserID, testUsername, testPassword);
        // checks that expected value is equal to actual value from getUsername
        assertEquals(testUsername, User.getUsername());
    }

    /**
     * Tests getPassword method.
     */
    @Test
    public void testGetPassword() {
        User.setUser(testUserID, testUsername, testPassword);
        assertTrue(User.checkPassword(testPassword, User.getPassword()));
}

    /**
     * Tests hashPassword method.
     */
    @Test
    public void testHashPassword() {
        String hashedPassword = User.hashPassword(testPassword);

        // checks that hashed password is not empty
        assertNotNull(hashedPassword);
        // checks that hashed password and plain password are different
        assertNotEquals(testPassword, hashedPassword);
        // checks that checkPassword returns true for matching passwords
        assertTrue(User.checkPassword(testPassword, hashedPassword));
    }

    /**
     * Tests checkPassword method.
     */
    @Test
    public void testCheckPassword() {
        String hashedPassword = User.hashPassword(testPassword);

        // checks that the checkPassword returns true for matching passwords
        assertTrue(User.checkPassword(testPassword, hashedPassword));
        // checks that the checkPassword returns true for non-matching passwords
        assertFalse(User.checkPassword("fakePassword", hashedPassword));
    }

}
