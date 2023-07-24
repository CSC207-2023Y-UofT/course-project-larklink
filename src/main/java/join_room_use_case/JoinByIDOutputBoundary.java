package join_room_use_case;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(int roomID);
    void prepareFailView(String error);

}
