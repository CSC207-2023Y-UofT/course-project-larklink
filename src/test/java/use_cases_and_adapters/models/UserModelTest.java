package use_cases_and_adapters.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.UserModel;

/**
 * This class tests UserModel.
 */
public class UserModelTest {
    private String username;
    private String password;
    private UserModel userModel;

    @BeforeEach
    public void setUp() {
        username = "Harry";
        password = "1234abcd";
        userModel = new UserModel(username, password);
    }

    /**
     * Tests getUsername method.
     */
    @Test
    public void testGetUsername() {
        // check that expected value is equal to actual value from getUsername
        assert username.equals(userModel.getUsername());
    }

    /**
     * Tests getPassword method.
     */
    @Test
    public void testGetPassword() {
        // check that expected value is equal to actual value from getPassword
        assert password.equals(userModel.getPassword());
    }
}
