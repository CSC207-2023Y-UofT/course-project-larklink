package views;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WelcomeViewTest {

    @Test
    void testCreateSignUpButton() {
        // Create mock objects for UserSignupController
        UserSignupController signupController = mock(UserSignupController.class);

        // Create the WelcomeView
        WelcomeView welcomeView = new WelcomeView(null, signupController);

        // Call the method
        JButton signUpButton = welcomeView.createSignUpButton();

        // Verify the button text and ActionListener
        assertEquals("Sign up", signUpButton.getText());

        // Verify that clicking the button opens the SignupView
        signUpButton.doClick();
    }

    @Test
    void testCreateLoginButton() {
        // Create mock objects for UserLoginController
        UserLoginController loginController = mock(UserLoginController.class);

        // Create the WelcomeView
        WelcomeView welcomeView = new WelcomeView(loginController, null);

        // Call the method
        JButton loginButton = welcomeView.createLoginButton();

        // Verify the button text and ActionListener
        assertEquals("Log in", loginButton.getText());

        // Verify that clicking the button opens the LoginView
        loginButton.doClick();
    }

    // Additional tests for createPanel() method and other GUI interactions can be written,
    // but might require specialized GUI testing frameworks.
}

