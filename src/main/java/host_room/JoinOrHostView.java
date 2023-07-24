package host_room;

import javax.swing.*;
import java.awt.*;

public class JoinOrHostView {
    private final HostRoomController controller;
    private final Integer userID;

    public JoinOrHostView (HostRoomController controller, Integer userID) {
        this.controller = controller;
        this.userID = userID;
    }
    public void prepareGUI() {
        JFrame frame = createFrame();
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("Create or Join");
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
        JButton hostButton = createHostButton();


        panel.add(new JLabel());
        panel.add(hostButton);
        return panel;
    }
    private JButton createHostButton() {
        JButton submitButton = new JButton("Host");
        submitButton.addActionListener(e -> {
            controller.handleHostRoom(userID);
        });
        return submitButton;
    }
}
