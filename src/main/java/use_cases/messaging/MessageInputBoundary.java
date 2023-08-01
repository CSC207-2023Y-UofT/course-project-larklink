package use_cases.messaging;

public interface MessageInputBoundary {
    void handleSendMessage(String content);
    void handleRetrieveMessages();
}
