package messaging;

public interface MessageInputBoundary {
    void handleSendMessage(MessageModel msgModel);
    void handleRetrieveMessages(MessageModel msgModel);
}
