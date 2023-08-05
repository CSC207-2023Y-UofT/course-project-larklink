package views;

import use_cases_and_adapters.Viewable;

import javax.swing.*;
import java.awt.*;

/**
 * Defines the constants and methods shared by all Views. All other
 * views will inherit from View
 */

public abstract class View extends JFrame implements Viewable {
    protected static final String TITLE = "Larklink";
    protected static final int WIDTH = 600;
    protected static final int HEIGHT = 400;

    private static JFrame currentFrame = null;
    protected static String messageHistory = "";

    /**
     * Creates a JFrame window using the standard class variables
     * and sets it to current frame
     * @param msgHistory list of previously sent messages in room
     */
    @Override
    public void prepareGUI(String msgHistory) {

        if (msgHistory != null) {
            View.messageHistory = msgHistory;
        }

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
     * Displays a pop-up message on the screen (like the method name suggests)
     * @param msg The message to be displayed
     */
    @Override
    public void displayPopUpMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Constructs the view-specific ui elements
     * @return JPanel containing the ui components
     */
    abstract public JPanel createPanel();
}
