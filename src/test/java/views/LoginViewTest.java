package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;

import javax.swing.*;

import static org.mockito.Mockito.*;


class LoginViewTest {
    private UserLoginController loginController;
    private LoginView loginView;

    @BeforeEach
    void setUp() {
        loginController = mock(UserLoginController.class);
        loginView = new LoginView(loginController);
        loginView.createPanel();
    }

    @Test
    void testCreatePanel() {
        JPanel panel = loginView.createPanel();
        assert panel != null;

        assert loginView.usernameField != null;
        assert loginView.passwordField != null;
    }

    @Test
    void testCreateLoginButton_ValidCredentials() {
        JButton loginButton = loginView.createLoginButton();
        assert loginButton != null;

        loginView.usernameField = new JTextField("username");
        loginView.passwordField = new JPasswordField("password");
        loginButton.doClick();

        // should be called
        verify(loginController).formatAndHandleUserLogin("username", "password");
    }

    @Test
    void testCreateLoginButton_InvalidCredentials() {
        JButton loginButton = loginView.createLoginButton();
        assert loginButton != null;

        loginView.usernameField = new JTextField("username");
        // no password is inavlid
        loginView.passwordField = new JPasswordField("");

        loginButton.doClick();
        // shouldn't be called because invalid credentials
        verify(loginController, never()).formatAndHandleUserLogin(any(), any());
    }
}