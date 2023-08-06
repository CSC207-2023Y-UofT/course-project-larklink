package use_cases_and_adapters.user_signup;

import org.junit.jupiter.api.*;
import org.mockito.*;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupInputBoundary;

import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests UserSignupController.
 */
public class UserSignupControllerTest {
    private UserSignupController controller;
    @Mock
    private UserSignupInputBoundary inputBoundary;
    @Captor
    ArgumentCaptor<UserModel> userModelCaptor;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        controller = new UserSignupController(inputBoundary);
    }

    /**
     * Tests formatAndHandleUserSignup with the given username and password.
     */
    @Test
    public void testFormatAndHandleUserSignup() {
        String testUsername = "testUser";
        String testPassword = "testPassword";

        controller.formatAndHandleUserSignup(testUsername, testPassword);

        // we use userModelCaptor since UserModel doesn't implement equals
        // checks that inputBoundary calls handleUserSignup method exactly once with the correct UserModel object
        Mockito.verify(inputBoundary, Mockito.times(1)).handleUserSignup(userModelCaptor.capture());

        UserModel capturedUser = userModelCaptor.getValue(); // get the argument that was passed to handleUserSignup

        // checks that correct username and password are passed to this method
        assert capturedUser.getUsername().equals(testUsername);
        assert capturedUser.getPassword().equals(testPassword);
    }
}
