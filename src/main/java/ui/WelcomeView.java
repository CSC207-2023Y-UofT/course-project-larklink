package ui;

import signup_and_login.user_login.UserLoginController;
import signup_and_login.user_signup.UserSignupController;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class WelcomeView extends View {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,}$");
    private final UserLoginController loginController;
    private final UserSignupController signupController;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public WelcomeView(UserLoginController loginController, UserSignupController signupController) {
        this.loginController = loginController;
        this.signupController = signupController;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(90, 70, 90, 70));

        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        JButton signupButton = createSignUpButton();
        JButton loginButton = createLoginButton();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(signupButton);
        panel.add(loginButton);
        return panel;
    }

    private JButton createSignUpButton() {
        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (welcomeViewHelper(username, password)){
                signupController.formatAndHandleUserSignup(username, password);
            }
        });
        return signupButton;
    }

    private JButton createLoginButton() {
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (welcomeViewHelper(username, password)){
                loginController.formatAndHandleUserLogin(username, password);
            }
        });
        return loginButton;

    }

    private boolean welcomeViewHelper(String username, String password){

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Invalid username! Use only alphanumeric characters. Minimum length: 3",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Password field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            JOptionPane.showMessageDialog(null,
                    "Password too short! Minimum length: " + MIN_PASSWORD_LENGTH,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Invalid password! Use only alphanumeric characters.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
