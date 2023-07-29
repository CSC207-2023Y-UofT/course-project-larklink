package use_case.messaging;

public interface MessageOutputBoundary {

    void prepareRoomView(String messageHistory);
    // void prepareRoomViewForLark(List<MessageDBModel> messageList);
    void prepareMessageErrorView();
}
