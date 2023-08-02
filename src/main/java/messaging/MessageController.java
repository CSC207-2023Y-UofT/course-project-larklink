package messaging;
/**
 * The MessageController class is responsible for handling user requests related to messages in the application.
 * It acts as an intermediary between the user interface and the MessageInputBoundary, delegating the requests to the appropriate methods in the input boundary.
 */
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