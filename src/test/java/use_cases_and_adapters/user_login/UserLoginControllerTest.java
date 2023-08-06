package use_cases_and_adapters.user_login;


import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginInputBoundary;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests UserLoginController.
 */
public class UserLoginControllerTest {
    private UserLoginController controller;
    @Mock
    private UserLoginInputBoundary inputBoundary;
    @Captor
    ArgumentCaptor<UserModel> userModelCaptor;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        controller = new UserLoginController(inputBoundary);
    }

    /**
     * Tests formatAndHandleUserLogin with the given username and password.
     */
    @Test
    public void testFormatAndHandleUserSignup() {
        String testUsername = "testUser";
        String testPassword = "testPassword";

        controller.formatAndHandleUserLogin(testUsername, testPassword);

        // we use userModelCaptor since UserModel doesn't implement equals
        // checks that inputBoundary calls handleUserLogin exactly once with correct UserModel object
        Mockito.verify(inputBoundary, Mockito.times(1)).handleUserLogin(userModelCaptor.capture());

        UserModel capturedUser = userModelCaptor.getValue(); // get the argument that was passed to handleUserLogin

        // checks that correct username and password are passed to this method
        assert capturedUser.getUsername().equals(testUsername);
        assert capturedUser.getPassword().equals(testPassword);
    }
}
