package use_cases_and_adapters.messaging;

public interface MessageOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareMessageErrorView();
}
