package screen;

import javax.swing.*;
import java.awt.*;

public class JoinOrHostView {

    private final int currUserID;
    private final JoinByIDController joinByIDController;

    public JoinOrHostView(int currUserID, JoinByIDController joinByIDController){
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
        JFrame frame = new JFrame("Welcome to LarkLink");
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JButton joinButton = createJoinButton();
        JButton hostButton = createHostButton();

        panel.add(joinButton);
        panel.add(hostButton);
        return panel;
    }

    private JButton createJoinButton() {
        JButton joinButton = new JButton("Join a Room");
        joinButton.addActionListener(e -> {
            JoinByIDView joinByIDView = new JoinByIDView(currUserID, joinByIDController);
            joinByIDView.prepareGUI();
        });
        return joinButton;
    }

    private JButton createHostButton() {
        JButton hostButton = new JButton("Host a Room");
        return hostButton;
    }



}
