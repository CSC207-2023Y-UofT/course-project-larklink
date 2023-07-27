package ui;

import host_room.HostRoomController;
import join_room.JoinByIDController;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class JoinOrHostView {
    private static final Pattern ROOM_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");
    private static final int MIN_ROOMNAME_LENGTH = 5;
    private final Integer currUserID;
    private final JoinByIDController joinByIDController;
    private final HostRoomController hostRoomController;
    private JTextField roomField;

    public JoinOrHostView(JoinByIDController joinByIDController, HostRoomController hostRoomController,
                          Integer currUserID){
        this.currUserID = currUserID;
        this.joinByIDController = joinByIDController;
        this.hostRoomController = hostRoomController;
    }


    public void prepareGUI() {
        JFrame frame = createFrame();
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private JFrame createFrame() {
        JFrame frame = new JFrame("Host or Join");
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        roomField = new JTextField();
        JButton joinButton = createJoinButton();
        JButton hostButton = createHostButton();

        panel.add(roomField);
        panel.add(hostButton);
        panel.add(joinButton);
        return panel;
    }

    private JButton createHostButton() {
        JButton hostButton = new JButton("Host Room");
        hostButton.addActionListener(e -> {
            String name = roomField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Room Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ROOM_NAME_PATTERN.matcher(name).matches()) {
                JOptionPane.showMessageDialog(null, "Invalid name! Use only alphanumeric characters. Minimum length: 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.length() < MIN_ROOMNAME_LENGTH) {
                JOptionPane.showMessageDialog(null, "Name too short! Minimum length: " + MIN_ROOMNAME_LENGTH, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hostRoomController.handleHostRoom(currUserID, name);
        });
        return hostButton;
    }


    private JButton createJoinButton() {
        JButton joinButton = new JButton("Join Room");
        //CA violation?
        joinButton.addActionListener(e -> {
            JoinByIDView joinByIDView = new JoinByIDView(currUserID, joinByIDController);
            joinByIDView.prepareGUI();
        });
        return joinButton;
    }



}
