package use_cases_and_adapters;
import javax.swing.*;

/**
 * This class is an abstraction layer between presenters and views.
 */
public interface Viewable {
    JPanel createPanel();

    void prepareGUI();
}