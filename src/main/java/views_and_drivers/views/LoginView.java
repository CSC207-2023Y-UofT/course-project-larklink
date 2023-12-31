package views_and_drivers.views;

import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;

import javax.swing.*;
import java.awt.*;


/**
 * View for the log in use case
 */
public class LoginView extends View {
    private final UserLoginController loginController;
    public JTextField usernameField;
    public JPasswordField passwordField;

    public LoginView(UserLoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Creates a panel with a username field and a password field as well as a button
     * that calls the login controller and one that lets you go back
     * @return JPanel with the specified fields
     */
    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));

        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        JButton loginButton = createLoginButton();
        JButton goBackButton = createGoBackButton();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(goBackButton);
        panel.add(loginButton);
        return panel;
    }

    /**
     * Creates a button that grabs the text from username and password fields and attempts to
     * log in the user by calling the login controller
     * @return JButton with the described functionality
     */
    protected JButton createLoginButton() {
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (ValidationHelper.isUsernameValid(username) && ValidationHelper.isPasswordValid(password)){
                loginController.formatAndHandleUserLogin(username, password);
            }
        });
        return loginButton;

    }

    /**
     * Creates a button that allows the user to go back to the welcome view
     * @return JButton with the described functionality
     */
    protected JButton createGoBackButton() {
        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(e -> ViewManager.switchToWelcomeView());
        return goBackButton;
    }
}
