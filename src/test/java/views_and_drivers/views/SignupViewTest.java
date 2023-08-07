package views_and_drivers.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import javax.swing.*;

import static org.mockito.Mockito.*;

class SignupViewTest {

    private UserSignupController signupController;
    private SignupView signupView;

    @BeforeEach
    void setUp() {
        signupController = mock(UserSignupController.class);
        signupView = new SignupView(signupController);
    }
    /*
    @Test
    void testCreatePanel() {
        JPanel panel = signupView.createPanel();
        assert panel != null;

        // make sure all fields and buttons displayed
        JTextField usernameField =  ViewTestUtility.findElement(panel, JTextField.class);
        assert usernameField != null;

        JPasswordField passwordField =  ViewTestUtility.findElement(panel, JPasswordField.class);
        assert passwordField != null;

        JButton signupButton = ViewTestUtility.findElement(panel, JButton.class);
        assert signupButton != null;

        JPasswordField repeatPasswordField =  ViewTestUtility.findElement(panel, JPasswordField.class);
        assert repeatPasswordField != null;
    }

     */
}