package use_cases_and_adapters.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.UserDBModel;

/**
 * This class tests UserDBModel.
 */
public class UserDBModelTest {
    private Integer id;
    private String username;
    private String password;
    private UserDBModel testModel;

    @BeforeEach
    public void setUp() {
        id = 7;
        username = "Harry";
        password = "12345678";
        testModel = new UserDBModel(id, username, password);
    }

    /**
     * Test getUserID method.
     */
    @Test
    public void testGetUserID() {
        //check that expected value is equal to actual value from getUserID
        assert id.equals(testModel.getUserID());
    }

    /**
     * Test getUsername method.
     */
    @Test
    public void testGetUsername() {
        //check that expected value is equal to actual value from getUsername
        assert username.equals(testModel.getUsername());
    }

    /**
     * Test getPassword method.
     */
    @Test
    public void testGetPassword() {
        //check that expected value is equal to actual value from getPassword
        assert password.equals(testModel.getPassword());
    }

}
