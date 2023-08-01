package views;

import use_cases.join_room.JoinByIDController;

import javax.swing.*;
import java.awt.*;

public class JoinByIDView extends View {

    private JTextField nameField;
    private final JoinByIDController controller;

    public JoinByIDView(JoinByIDController controller){
        this.controller = controller;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));

        nameField = new JTextField();
        JButton joinButton = createJoinButton();

        panel.add(nameField);
        panel.add(joinButton);
        return panel;
    }

    private JButton createJoinButton() {
        JButton submitButton = new JButton("Join");
        submitButton.addActionListener(e -> {
            String roomName = nameField.getText();

            if (roomName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Room Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.formatAndHandleJoinByID(roomName);
        });
        return submitButton;
    }




}
