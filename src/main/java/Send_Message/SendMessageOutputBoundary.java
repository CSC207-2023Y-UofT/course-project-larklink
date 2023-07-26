package send_message;

public interface SendMessageOutputBoundary {

    void prepareRoomView(Integer RoomID, Integer currUserID, String message);

    void prepareRoomViewForLark();
    void prepareMessageErrorView();
}
