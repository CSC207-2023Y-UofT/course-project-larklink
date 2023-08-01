package messaging;

public interface MessageOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareMessageErrorView();
}
