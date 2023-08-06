package entities;

import org.junit.jupiter.api.*;

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
        assert User.getUserID().equals(testUserID);
        assert User.getUsername().equals(testUsername);
        assert User.checkPassword(testPassword, User.getPassword());
    }

    @Test
    public void testCheckPassword() {
        String hashedPassword = User.hashPassword(testPassword);
        assert User.checkPassword(testPassword, hashedPassword);
        assert !User.checkPassword("fakePassword", hashedPassword);
    }
}
