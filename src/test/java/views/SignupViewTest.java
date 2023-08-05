package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupViewTest {

    private UserSignupController signupController;
    private SignupView signupView;

    @BeforeEach
    void setUp() {
        signupController = mock(UserSignupController.class);
        signupView = new SignupView(signupController);
    }

    @Test
    void testCreatePanel() {
        JPanel panel = signupView.createPanel();
        assertNotNull(panel);

        // Test the existence of specific components by their type
        JTextField usernameField = findComponentByType(panel, JTextField.class);
        assertNotNull(usernameField);

        JPasswordField passwordField = findComponentByType(panel, JPasswordField.class);
        assertNotNull(passwordField);

        JPasswordField repeatPasswordField = findComponentByType(panel, JPasswordField.class);
        assertNotNull(repeatPasswordField);

        JButton signupButton = findComponentByType(panel, JButton.class);
        assertNotNull(signupButton);
    }

    // Utility method to find the component of a specific type in the container
    private <T extends JComponent> T findComponentByType(Container container, Class<T> type) {
        for (Component component : container.getComponents()) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
            if (component instanceof Container) {
                T result = findComponentByType((Container) component, type);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}