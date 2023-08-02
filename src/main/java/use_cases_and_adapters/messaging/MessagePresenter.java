package use_cases_and_adapters.messaging;
import javax.swing.*;
import views.View;
import views.Viewable;

public class MessagePresenter implements MessageOutputBoundary {
    private Viewable view;

    /**
     * Prepares the room view with the given message history.
     *
     * @param messageHistory The message history to be displayed in the room view.
     */
    @Override
    public void prepareRoomView(String messageHistory) {
        View.messageHistory = messageHistory;
        view.prepareGUI();
    }

    /**
     * Prepares an error view for displaying a message sending error.
     */
    @Override
    public void prepareMessageErrorView() {
        JOptionPane.showMessageDialog(null, "Error sending message, cannot send an empty message!", "Message Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Sets the view to be used for presentation.
     *
     * @param view The view to be used for presenting messages.
     */
    public void setView(Viewable view) {
        this.view = view;
    }
}