package views_and_drivers.views;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import static org.mockito.Mockito.*;

class ViewManagerTest {
    /*
    @Test
    void testStartWelcomeView() {
        UserLoginController loginController = mock(UserLoginController.class);
        UserSignupController signupController = mock(UserSignupController.class);

        // mock view
        WelcomeView welcomeView = mock(WelcomeView.class);

        ViewManager.welcomeView = welcomeView;
        ViewManager.startWelcomeView(loginController, signupController);
    }

    @Test
    void testSwitchToWelcomeView() {
        WelcomeView welcomeView = mock(WelcomeView.class);
        ViewManager.welcomeView = welcomeView;

        ViewManager.switchToWelcomeView();
        verify(welcomeView, times(1)).prepareGUI(null);
    }

     */
}
