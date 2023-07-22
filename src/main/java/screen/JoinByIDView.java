package screen;

import javax.swing.*;
import java.awt.*;

public class JoinByIDView {

    private JTextField roomNameField;
    private JTextField currUserIDField;
    private final JoinByIDController controller;

    public JoinByIDView(JoinByIDController controller){
        this.controller = controller;
    }

    public void prepareGUI() {
        JFrame frame = createFrame();
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private JFrame createFrame() {
        JFrame frame = new JFrame("Joining a Room");
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel roomNameLabel = new JLabel("Room name:");
        roomNameField = new JTextField();
        JLabel currUserIDLabel = new JLabel("User name:");
        currUserIDField = new JTextField();
        JButton joinButton = createJoinButton();

        panel.add(roomNameLabel);
        panel.add(roomNameField);
        panel.add(currUserIDLabel);
        panel.add(currUserIDField);
        panel.add(new JLabel());
        panel.add(joinButton);
        return panel;
    }

    private JButton createJoinButton() {
        JButton submitButton = new JButton("Join");
        submitButton.addActionListener(e -> {
            String roomName = roomNameField.getText();
            String currUserID = currUserIDField.getText();

            if (roomName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Room Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (currUserID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter User Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            controller.formatAndHandleJoinByID(roomName, currUserID);
        });
        return submitButton;
    }



}
