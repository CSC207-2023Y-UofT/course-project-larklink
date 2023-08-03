package use_cases_and_adapters.messaging;

/**
 * The MessageInputBoundary interface represents the input boundary for handling message-related requests.
 * It defines methods for sending a message and retrieving messages.
 */
public interface MessageInputBoundary {
    /**
     * Handles the user request to send a message.
     *
     * @param content The content of the message to be sent.
     */
    void handleSendMessage(String content);

    /**
     * Handles the user request to retrieve messages.
     */
    void handleRetrieveMessages();
}
