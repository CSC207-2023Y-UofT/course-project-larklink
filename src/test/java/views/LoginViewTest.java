package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.*;
import java.lang.reflect.Field;


class LoginViewTest {
    private UserLoginController loginController;
    private LoginView loginView;

    @BeforeEach
    void setUp() {
        loginController = mock(UserLoginController.class);
        loginView = new LoginView(loginController);
        // Call createPanel() to initialize the fields
        loginView.createPanel();
    }

    @Test
    void testCreatePanel() {
        JPanel panel = loginView.createPanel();
        assertNotNull(panel);

        // Access the public fields directly
        assertNotNull(loginView.usernameField);
        assertNotNull(loginView.passwordField);
    }

    @Test
    void testCreateLoginButton_ValidCredentials() {
        JButton loginButton = loginView.createLoginButton();
        assertNotNull(loginButton);

        // Mocking user input for the username and password fields
        loginView.usernameField = new JTextField("validUsername");
        loginView.passwordField = new JPasswordField("validPassword");

        loginButton.doClick(); // Simulate a click on the "Log in" button

        // Verify that the controller method is called with the correct arguments
        verify(loginController).formatAndHandleUserLogin("validUsername", "validPassword");
    }

    @Test
    void testCreateLoginButton_InvalidCredentials() {
        JButton loginButton = loginView.createLoginButton();
        assertNotNull(loginButton);

        // Mocking user input for the username and password fields
        loginView.usernameField = new JTextField("invalidUsername");
        loginView.passwordField = new JPasswordField(""); // Invalid password (empty)

        loginButton.doClick(); // Simulate a click on the "Log in" button

        // Verify that the controller method is not called since the credentials are invalid
        verify(loginController, never()).formatAndHandleUserLogin(any(), any());
    }

    // Helper method to get components by their name in the panel
    private Component getComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName())) {
                return component;
            }
            if (component instanceof Container) {
                Component found = getComponentByName((Container) component, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}