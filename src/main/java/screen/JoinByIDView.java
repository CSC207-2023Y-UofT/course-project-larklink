package screen;

import javax.swing.*;
import java.awt.*;

public class JoinByIDView {

    private JTextField roomIDField;
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
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel roomIDLabel = new JLabel("Room name:");
        roomIDField = new JTextField();
        JLabel currUserIDLabel = new JLabel("Username:");
        currUserIDField = new JTextField();
        JButton joinButton = createJoinButton();

        panel.add(roomIDLabel);
        panel.add(roomIDField);
        panel.add(currUserIDLabel);
        panel.add(currUserIDField);
        panel.add(new JLabel());
        panel.add(joinButton);
        return panel;
    }

    private JButton createJoinButton() {
        JButton submitButton = new JButton("Join");
        submitButton.addActionListener(e -> {
            String roomID = roomIDField.getText();
            String currUserID = currUserIDField.getText();

            if (roomID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Room Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (currUserID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter User Name",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            controller.formatAndHandleJoinByID(roomID, currUserID);
        });
        return submitButton;
    }



}
