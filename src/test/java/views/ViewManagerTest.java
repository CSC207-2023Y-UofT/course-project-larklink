package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;



import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ViewManagerTest {

    @Test
    void testStartWelcomeView() {
        // Create mock objects for UserLoginController and UserSignupController
        UserLoginController loginController = mock(UserLoginController.class);
        UserSignupController signupController = mock(UserSignupController.class);

        // Create a mock WelcomeView
        WelcomeView welcomeView = mock(WelcomeView.class);

        // Call the method
        ViewManager.welcomeView = welcomeView;
        ViewManager.startWelcomeView(loginController, signupController);

    }

    @Test
    void testSwitchToWelcomeView() {
        // Create a mock WelcomeView
        WelcomeView welcomeView = mock(WelcomeView.class);
        ViewManager.welcomeView = welcomeView;

        // Call the method
        ViewManager.switchToWelcomeView();

        // Verify that the prepareGUI method is called on the WelcomeView mock
        verify(welcomeView, times(1)).prepareGUI(null);
    }
}
