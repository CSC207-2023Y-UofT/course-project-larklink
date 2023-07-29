package ui;

import use_case.host_room.HostRoomController;
import use_case.join_room.JoinByIDController;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class JoinOrHostView extends View {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{5,}$");
    private static final int MIN_PASSWORD_LENGTH = 5;
    private final HostRoomController hostRoomController;
    private final JoinByIDController joinByIDController;

    private JTextField roomField;

    public JoinOrHostView(HostRoomController hostRoomController, JoinByIDController joinByIDController) {
        this.hostRoomController = hostRoomController;
        this.joinByIDController = joinByIDController;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        this.roomField = new JTextField();
        JLabel roomLabel = new JLabel("Room Name:");
        JButton hostButton = createHostButton();
        JButton joinButton = createJoinButton();
        JLabel orLabel = new JLabel("or");
        orLabel.setHorizontalAlignment(JLabel.CENTER);
        orLabel.setVerticalAlignment(JLabel.CENTER);


        panel.add(roomLabel);
        panel.add(roomField);
        panel.add(hostButton);
        panel.add(new JLabel());
        panel.add(orLabel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(joinButton);
        panel.add(new JLabel());

        return panel;
    }

    private JButton createHostButton() {

        JButton hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            String roomField = this.roomField.getText();
            if (!NAME_PATTERN.matcher(roomField).matches()) {
                JOptionPane.showMessageDialog(null, "Invalid Room name! Use only alphanumeric characters. Minimum length: 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomField.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Room Name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomField.length() < MIN_PASSWORD_LENGTH) {
                JOptionPane.showMessageDialog(null, "Room Name too short! Minimum length: " + MIN_PASSWORD_LENGTH, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hostRoomController.handleHostRoom(userID, roomField);
        });
        return hostButton;
    }

    private JButton createJoinButton() {

        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(e -> {
            JoinByIDView joinByIDView = new JoinByIDView(joinByIDController);
            joinByIDView.prepareGUI();
        });
        return joinButton;
    }
}
