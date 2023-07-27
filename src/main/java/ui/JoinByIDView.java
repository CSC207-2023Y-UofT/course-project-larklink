package ui;

import join_room.JoinByIDController;

import javax.swing.*;
import java.awt.*;

public class JoinByIDView {

    private JTextField roomNameField;
    private final JoinByIDController joinByIDController;
    private final Integer currUserID;

    public JoinByIDView(Integer currUserID, JoinByIDController joinByIDController){
        this.currUserID = currUserID;
        this.joinByIDController = joinByIDController;
    }

    public void prepareGUI() {
        JFrame frame = createFrame();
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private JFrame createFrame() {
        JFrame frame = new JFrame("Please Enter Room Name");
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));

        roomNameField = new JTextField();
        JButton joinButton = createJoinButton();

        panel.add(roomNameField);
        panel.add(joinButton);
        return panel;
    }

    private JButton createJoinButton() {
        JButton submitButton = new JButton("Join");
        submitButton.addActionListener(e -> {
            String roomName = roomNameField.getText();
            joinByIDController.formatAndHandleJoinByID(roomName, currUserID);
        });
        return submitButton;
    }



}
