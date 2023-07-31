package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testSetUser() {
        User.setUser(testUserID, testUsername, testPassword);

        assertEquals(testUserID, User.getUserID());
        assertEquals(testUsername, User.getUsername());
        assertTrue(User.checkPassword(testPassword, User.getPassword()));
    }

    @Test
    public void testCheckPassword() {
        String hashedPassword = User.hashPassword(testPassword);

        assertTrue(User.checkPassword(testPassword, hashedPassword));
        assertFalse(User.checkPassword("fakePassword", hashedPassword));
    }

}
