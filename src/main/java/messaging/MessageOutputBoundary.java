package messaging;

/**
 * The MessageOutputBoundary interface defines two methods for preparing views related to messages.
 */
public interface MessageOutputBoundary {
    /**
     * Prepares the room view with the provided message history.
     *
     * @param messageHistory The message history to be displayed in the room view.
     */
    void prepareRoomView(String messageHistory);

    /**
     * Prepares an error view for messages when the content is empty or invalid.
     */
    void prepareMessageErrorView();
}
