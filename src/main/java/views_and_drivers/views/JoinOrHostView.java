package views_and_drivers.views;

import use_cases_and_adapters.host_room.HostRoomController;
import use_cases_and_adapters.join_room.JoinRoomController;

import javax.swing.*;
import java.awt.*;

/**
    View for the join room and host room use cases
 **/
public class JoinOrHostView extends View {
    private final HostRoomController hostRoomController;
    private final JoinRoomController joinRoomController;

    protected JTextField roomField;

    public JoinOrHostView(HostRoomController hostRoomController, JoinRoomController joinRoomController) {
        this.hostRoomController = hostRoomController;
        this.joinRoomController = joinRoomController;
    }

    /**
     * Creates a panel with a list of joinable rooms and a field to enter the name of a new room
     * @return JPanel with the specified fields
     */
    @Override
    public JPanel createPanel() {
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

    /**
     * Constructs a button that when clicked triggers the creation of a new room
     * @return JButton with the text "host"
     */
    protected JButton createHostButton() {
        JButton hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            String roomName = roomField.getText();
            if (ValidationHelper.isRoomNameValid(roomName)) {
                hostRoomController.handleHostRoom(roomName);
            }
        });
        return hostButton;
    }
}
