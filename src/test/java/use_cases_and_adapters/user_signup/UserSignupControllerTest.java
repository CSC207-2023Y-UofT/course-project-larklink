package use_cases_and_adapters.user_signup;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupInputBoundary;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
        verify(inputBoundary, times(1)).handleUserSignup(userModelCaptor.capture());

        UserModel capturedUser = userModelCaptor.getValue(); // get the argument that was passed to handleUserSignup

        // checks that correct username and password are passed to this method
        assertEquals(testUsername, capturedUser.getUsername());
        assertEquals(testPassword, capturedUser.getPassword());
    }
}
