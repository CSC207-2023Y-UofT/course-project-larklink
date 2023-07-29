package messaging;

public interface MessageOutputBoundary {

    void prepareRoomView(String messageHistory);

    void prepareRoomViewForLark(String messageHistory);

    // void prepareRoomViewForLark(List<MessageDBModel> messageList);
    void prepareMessageErrorView();
}
