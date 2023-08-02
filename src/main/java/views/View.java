package views;

import use_cases_and_adapters.Viewable;

import javax.swing.*;
import java.awt.*;

/**
 * Defines the constants and methods shared by all Views. All other
 * views will inherit from View
 */

public abstract class View extends JFrame implements Viewable {
    private static final String TITLE = "Larklink";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public static String messageHistory = "";
    private static JFrame currentFrame = null;

    /**
     * Creates a JFrame window using the standard class variables
     * and sets it to current frame
     */
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

    /**
     * Constructs the view-specific ui elements
     * @return JPanel containing the ui components
     */
    abstract public JPanel createPanel();
}
