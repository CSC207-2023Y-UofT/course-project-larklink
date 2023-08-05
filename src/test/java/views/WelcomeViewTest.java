package views;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import javax.swing.*;
import static org.mockito.Mockito.*;

class WelcomeViewTest {

    @Test
    void testCreateSignUpButton() {
        UserSignupController signupController = mock(UserSignupController.class);
        WelcomeView welcomeView = new WelcomeView(null, signupController);
        JButton signUpButton = welcomeView.createSignUpButton();

        // verify click
        signUpButton.doClick();
    }

    @Test
    void testCreateLoginButton() {
        // Create mock objects for UserLoginController
        UserLoginController loginController = mock(UserLoginController.class);
        WelcomeView welcomeView = new WelcomeView(loginController, null);

        JButton loginButton = welcomeView.createLoginButton();
        loginButton.doClick();
    }
}

