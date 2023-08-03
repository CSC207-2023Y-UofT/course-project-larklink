package use_cases_and_adapters.user_login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginInputBoundary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Test class for UserLoginController.
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
     * Tests formatAndHandleUserLogin method with given username and password.
     */
    @Test
    public void testFormatAndHandleUserSignup() {
        String testUsername = "testUser";
        String testPassword = "testPassword";

        controller.formatAndHandleUserLogin(testUsername, testPassword);

        // we use userModelCaptor since UserModel doesn't implement equals
        // checks that inputBoundary calls handleUserLogin method exactly once with UserModel object
        verify(inputBoundary, times(1)).handleUserLogin(userModelCaptor.capture());

        UserModel capturedUser = userModelCaptor.getValue(); // get the argument that was passed to handleUserLogin

        // checks that correct username and password are passed to this method
        assertEquals(testUsername, capturedUser.getUsername());
        assertEquals(testPassword, capturedUser.getPassword());
    }
}
