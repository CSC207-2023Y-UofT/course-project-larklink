package views;

import use_case.Viewable;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame implements Viewable {
    private static final String TITLE = "Larklink";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public static String messageHistory = "";
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

    abstract public JPanel createPanel();
}
