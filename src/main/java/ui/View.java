package ui;

import javax.swing.*;
import java.awt.*;

public abstract class View {
    private static final String TITLE = "Larklink";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public static Integer userID;
    public static Integer roomID;

    private static JFrame currentFrame = null;

    public void prepareGUI() {

        if(currentFrame != null) {
            currentFrame.dispose(); // close previous frame
        }

        JFrame frame = new JFrame(TITLE);
        frame.setLayout(new BorderLayout());
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        currentFrame = frame; // store reference to current frame (for when we want to close it)
    }

    abstract protected JPanel createPanel();

}
