package use_cases_and_adapters;
import javax.swing.*;

public interface Viewable {
    JPanel createPanel();

    void prepareGUI();
}