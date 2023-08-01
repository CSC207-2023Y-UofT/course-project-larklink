package messaging;

public interface MessageInputBoundary {
    void handleSendMessage(String content);
    void handleRetrieveMessages();
}
