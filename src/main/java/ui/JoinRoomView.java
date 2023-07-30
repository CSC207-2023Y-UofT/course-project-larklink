package ui;

import join_room.JoinRoomController;

import javax.swing.*;
import java.awt.*;

public class JoinRoomView extends View {

    private JTextField nameField;
    private final JoinRoomController controller;

    public JoinRoomView(JoinRoomController controller){
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

            controller.formatAndHandleJoinRoom(roomName);
        });
        return submitButton;
    }




}
