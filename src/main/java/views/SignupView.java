package views;

import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

import javax.swing.*;
import java.awt.*;

public class SignupView extends View{
    private final UserSignupController signupController;
    private JTextField usernameField;
    private JPasswordField PasswordField;
    private JPasswordField repeatPasswordField;

    public SignupView(UserSignupController signupController) {
        this.signupController = signupController;
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));

        this.usernameField = new JTextField();
        this.PasswordField = new JPasswordField();
        this.repeatPasswordField = new JPasswordField();
        JButton signupButton = createSignUpButton();
        JButton goBackButton = createGoBackButton();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(PasswordField);
        panel.add(new JLabel("Repeat Password:"));
        panel.add(repeatPasswordField);
        panel.add(goBackButton);
        panel.add(signupButton);
        return panel;
    }

    protected JButton createSignUpButton() {
        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(PasswordField.getPassword());
            String repeatPassword = new String(repeatPasswordField.getPassword());

            if (ValidationHelper.isUsernameValid(username)
                    && ValidationHelper.isPasswordValid(password)
                    && ValidationHelper.isRepeatPasswordValid(password, repeatPassword)){
                signupController.formatAndHandleUserSignup(username, password);
            }
        });
        return signupButton;
    }

    protected JButton createGoBackButton() {
        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(e -> ViewManager.switchToWelcomeView());
        return goBackButton;
    }

}
