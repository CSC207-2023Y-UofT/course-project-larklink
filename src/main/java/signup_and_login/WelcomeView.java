package signup_and_login;

import javax.swing.*;
import java.awt.*;

public class WelcomeView {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final UserController controller;

    public WelcomeView(UserController controller){
        this.controller = controller;
    }

    /**
     * Prepares and displays the graphical user interface for user registration.
     * This method creates the necessary components such as labels, text fields, and buttons,
     * sets up the layout, and handles the submission of user information.
     */
    public void prepareGUI() {

        JFrame frame = new JFrame("User Registration");
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if(!username.isEmpty() && !password.isEmpty()) {
                controller.formatAndHandleUser(username, password);
            } else {
                JOptionPane.showMessageDialog(frame, "Both fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // Empty label for grid alignment
        panel.add(submitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}