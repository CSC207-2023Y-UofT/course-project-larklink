package signup_and_login;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class WelcomeView {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");
    private static final int MIN_PASSWORD_LENGTH = 8;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private final UserController controller;

    public WelcomeView(UserController controller){
        this.controller = controller;
    }

    public void prepareGUI() {
        JFrame frame = createFrame();
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("User Registration");
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton submitButton = createSubmitButton();

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(submitButton);
        return panel;
    }

    private JButton createSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!USERNAME_PATTERN.matcher(username).matches()) {
                JOptionPane.showMessageDialog(null, "Invalid username! Use only alphanumeric characters. Minimum length: 3", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.length() < MIN_PASSWORD_LENGTH) {
                JOptionPane.showMessageDialog(null, "Password too short! Minimum length: " + MIN_PASSWORD_LENGTH, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.formatAndHandleUser(username, password);
        });
        return submitButton;
    }
}
