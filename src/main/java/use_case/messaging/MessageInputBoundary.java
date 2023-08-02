package use_case.messaging;

public interface MessageInputBoundary {
    void handleSendMessage(String content);
    void handleRetrieveMessages();
}
