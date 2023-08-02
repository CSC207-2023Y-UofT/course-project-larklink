package use_cases_and_adapters.user_signup;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupInputBoundary;


import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSignupControllerTest {

    private UserSignupController userSignupController;

    @Mock
    private UserSignupInputBoundary inputBoundary;

    @Captor
    ArgumentCaptor<UserModel> userModelCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userSignupController = new UserSignupController(inputBoundary);
    }

    @Test
    public void testFormatAndHandleUser() {
        String testUsername = "testUser";
        String testPassword = "testPassword";

        // check that we format our data into a UserModel and call handleUser
        userSignupController.formatAndHandleUserSignup(testUsername, testPassword);

        // we use userModelCaptor since UserModel doesn't implement equals
        verify(inputBoundary).handleUserSignup(userModelCaptor.capture());

        UserModel capturedUser = userModelCaptor.getValue(); // get the argument that was passed to handleUser
        assertEquals(testUsername, capturedUser.getUsername()); // check that we passed in the right username
        assertEquals(testPassword, capturedUser.getPassword()); // check that we passed in the right password
    }
}
