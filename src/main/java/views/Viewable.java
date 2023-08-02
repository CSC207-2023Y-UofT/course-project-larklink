package views;
import javax.swing.*;

public interface Viewable {
    JPanel createPanel();

    void prepareGUI();
}