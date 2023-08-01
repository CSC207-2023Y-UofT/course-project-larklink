package use_cases_and_adapters.signup_and_login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_cases_and_adapters.signup_and_login.UserController;
import use_cases_and_adapters.signup_and_login.UserInputBoundary;
import use_cases_and_adapters.signup_and_login.UserModel;

import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserInputBoundary inputBoundary;

    @Captor
    ArgumentCaptor<UserModel> userModelCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(inputBoundary);
    }

    @Test
    public void testFormatAndHandleUser() {
        String testUsername = "testUser";
        String testPassword = "testPassword";

        // check that we format our data into a UserModel and call handleUser
        userController.formatAndHandleUser(testUsername, testPassword);

        // we use userModelCaptor since UserModel doesn't implement equals
        verify(inputBoundary).handleUser(userModelCaptor.capture());

        UserModel capturedUser = userModelCaptor.getValue(); // get the argument that was passed to handleUser
        assertEquals(testUsername, capturedUser.getUsername()); // check that we passed in the right username
        assertEquals(testPassword, capturedUser.getPassword()); // check that we passed in the right password
    }
}
