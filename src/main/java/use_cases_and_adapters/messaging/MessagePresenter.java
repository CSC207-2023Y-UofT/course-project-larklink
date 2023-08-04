package use_cases_and_adapters.messaging;

import use_cases_and_adapters.Viewable;

/**
 * The MessagePresenter class is responsible for preparing and presenting message-related views to the user.
 * It implements the MessageOutputBoundary interface to handle view preparation and error presentation.
 */
public class MessagePresenter implements MessageOutputBoundary {
    private Viewable view;

    /**
     * Prepares the room view with the given message history.
     *
     * @param messageHistory The message history to be displayed in the room view.
     */
    @Override
    public void prepareRoomView(String messageHistory) {
        view.prepareGUI(messageHistory);
    }

    /**
     * Prepares an error view for displaying a message sending error.
     */
    @Override
    public void prepareMessageErrorView() {
        view.displayPopUpMessage("You cannot send an empty message!");
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