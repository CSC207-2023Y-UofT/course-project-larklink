package use_cases_and_adapters.messaging;

public interface MessageInputBoundary {
    void handleSendMessage(String content);
    void handleRetrieveMessages();
}
