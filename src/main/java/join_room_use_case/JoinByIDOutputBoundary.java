package join_room_use_case;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(String roomName);
    void prepareFailView(String error);

}
