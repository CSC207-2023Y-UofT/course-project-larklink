package ui;

import host_room.HostRoomController;

import javax.swing.*;
import java.awt.*;

public class JoinOrHostView extends View {
    private final HostRoomController controller;
    private JTextField roomField;

    public JoinOrHostView(HostRoomController controller) {
        this.controller = controller;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton hostButton = createHostButton();

        JLabel roomLabel = new JLabel("Room Name:");
        roomField = new JTextField();

        panel.add(roomLabel);
        panel.add(roomField);
        panel.add(new JLabel());
        panel.add(hostButton);
        return panel;
    }

    private JButton createHostButton() {

        JButton submitButton = new JButton("Host");
        submitButton.addActionListener(e -> {
            controller.handleHostRoom(Main.userID, roomField.getText());
        });
        return submitButton;
    }
}
