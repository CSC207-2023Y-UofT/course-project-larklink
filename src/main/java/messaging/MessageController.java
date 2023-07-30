package messaging;
import models.MessageModel;

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
     * @param roomID  The ID of the room where the message is sent.
     * @param sender  The ID of the message sender.
     * @param content The content of the message.
     * @param isLark  A boolean indicating whether the message is a lark.
     */
    public void handleSendMessage(Integer roomID, Integer sender, String content, Boolean isLark) {
        MessageModel request = new MessageModel(roomID, content, sender, isLark);
        inputBoundary.handleSendMessage(request);
    }

    /**
     * Handles the user request to retrieve messages.
     *
     * @param roomID  The ID of the room from which messages are retrieved.
     * @param sender  The ID of the message sender (not used in retrieval, set to a default value).
     * @param content The content of the message (not used in retrieval, set to a default value).
     */
    public void handleRetrieveMessages(Integer roomID, Integer sender, String content) {
        MessageModel request = new MessageModel(roomID, content, sender, false);
        inputBoundary.handleRetrieveMessages(request);
    }
}
