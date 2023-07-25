package send_message;

public interface SendMessageOutputBoundary {
    void prepareRoomView(String roomID);
    void prepareRoomViewForLark();
    void prepareMessageErrorView();
}
