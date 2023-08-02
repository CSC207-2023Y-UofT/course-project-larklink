package views;

import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import javax.swing.*;
import java.awt.*;

/**
 * The view displayed when a user first connects to LarkLink. Contains two fields:
 * username and password.
 */

public class WelcomeView extends View {
    private final UserLoginController loginController;
    private final UserSignupController signupController;

    public WelcomeView(UserLoginController loginController, UserSignupController signupController) {
        this.loginController = loginController;
        this.signupController = signupController;
    }

    /**
     * Constructs the panel using the helper createButtonMethod. Consists of two
     * buttons: one to log in and the other to sign up
     * @return JPanel with the specified components
     */

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(130, 70, 130, 70));

        JButton signupButton = createSignUpButton();
        JButton loginButton = createLoginButton();

        panel.add(signupButton);
        panel.add(loginButton);
        return panel;
    }

    /**
     * Creates a sign up button for displaying in the welcome view panel
     * @return JButton with the text "Sign up"
     */
    private JButton createSignUpButton() {
        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(e -> {
            SignupView signUpView = new SignupView(this.signupController);
            signUpView.prepareGUI();
        });
        return signupButton;
    }

    /**
     * Creates a log in button for displaying in the welcome view panel
     * @return JButton with the text "Log in"
     */
    private JButton createLoginButton() {
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(e -> {
            LoginView logInView = new LoginView(this.loginController);
            logInView.prepareGUI();
        });
        return loginButton;

    }



}
