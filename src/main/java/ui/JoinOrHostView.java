package ui;

import host_room.HostRoomController;
import join_room.JoinRoomController;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class JoinOrHostView extends View {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{5,}$");
    private static final int MIN_PASSWORD_LENGTH = 5;
    private final HostRoomController hostRoomController;
    private final JoinRoomController joinRoomController;

    private JTextField roomField;

    public JoinOrHostView(HostRoomController hostRoomController, JoinRoomController joinRoomController) {
        this.hostRoomController = hostRoomController;
        this.joinRoomController = joinRoomController;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        // Room input and host button
        JPanel roomInputPanel = new JPanel();
        roomInputPanel.setLayout(new BoxLayout(roomInputPanel, BoxLayout.X_AXIS));
        roomField = new JTextField(15);
        JLabel roomLabel = new JLabel("Room Name:");
        JButton hostButton = createHostButton();
        roomInputPanel.add(roomLabel);
        roomInputPanel.add(roomField);
        roomInputPanel.add(hostButton);
        panel.add(roomInputPanel, BorderLayout.SOUTH);


        // Available Rooms
        JLabel availableRoomsLabel = new JLabel("Available Rooms:");
        panel.add(availableRoomsLabel, BorderLayout.NORTH);

        JPanel roomsPanel = new JPanel();
        roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));

        for (String room : joinRoomController.loadRoomNames()) {
            JPanel singleRoomPanel = new JPanel(new BorderLayout());
            singleRoomPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

            JLabel roomNameLabel = new JLabel(room);
            singleRoomPanel.add(roomNameLabel, BorderLayout.WEST);

            JButton joinButton = new JButton("Join");
            joinButton.addActionListener(e -> joinRoomController.handleJoinRoomByRoomName(room));
            singleRoomPanel.add(joinButton, BorderLayout.EAST);

            roomsPanel.add(singleRoomPanel);
        }

        // Scroll pane for the available rooms list
        JScrollPane scrollPane = new JScrollPane(roomsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JButton createHostButton() {
        JButton hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            String roomName = roomField.getText();
            if (!NAME_PATTERN.matcher(roomName).matches()) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Room name! Use only alphanumeric characters. Minimum length: 5",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomName.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Room Name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomName.length() < MIN_PASSWORD_LENGTH) {
                JOptionPane.showMessageDialog(null,
                        "Room Name too short! Minimum length: " + MIN_PASSWORD_LENGTH,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hostRoomController.handleHostRoom(roomName);
        });
        return hostButton;
    }
}
