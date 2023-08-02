package use_case.messaging;

public class MessageController {
    private final MessageInputBoundary inputBoundary;

    /**
     * Constructs a MessageController object with the given input boundary.
     *
     * @param inputBoundary The boundary for handling message-related requests.
     */
    public MessageController(MessageInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles the user request to send a message.
     *
     * @param content The content of the message.
     */
    public void handleSendMessage(String content){
        inputBoundary.handleSendMessage(content);
    }

    /**
     * Handles the user request to retrieve messages.
     */
    public void handleRetrieveMessages() {
        inputBoundary.handleRetrieveMessages();
    }
}