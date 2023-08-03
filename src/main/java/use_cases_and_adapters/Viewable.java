package use_cases_and_adapters;
/**
 * This class is an abstraction layer between presenters and views.
 */
public interface Viewable {
    void prepareGUI(String msgHistory);
    void displayPopUpMessage(String msg);
}