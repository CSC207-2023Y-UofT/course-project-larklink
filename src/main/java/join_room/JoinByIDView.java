package join_room;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class JoinByIDView {

    private static final Pattern ROOM_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");
    private static final int MIN_ROOMNAME_LENGTH = 5;
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
        JFrame frame = new JFrame("Please Enter a Room Name");
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

            if (roomName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Room Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ROOM_NAME_PATTERN.matcher(roomName).matches()) {
                JOptionPane.showMessageDialog(null, "Invalid name! Use only alphanumeric characters. Minimum length: 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (roomName.length() < MIN_ROOMNAME_LENGTH) {
                JOptionPane.showMessageDialog(null, "Name too short! Minimum length: " + MIN_ROOMNAME_LENGTH, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            joinByIDController.formatAndHandleJoinByID(roomName, currUserID);
        });
        return submitButton;
    }



}
