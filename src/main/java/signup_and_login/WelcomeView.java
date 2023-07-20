package signup_and_login;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeView {
    private String usernameField;
    private String passwordField;
    private UserController controller;

    public WelcomeView(UserController controller){
        this.controller = controller;
    }

    public void prepareGUI() {
        JFrame frame  = new JFrame();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.formatAndHandleUser(usernameField, passwordField);
            }
        });
        frame.add(submitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}