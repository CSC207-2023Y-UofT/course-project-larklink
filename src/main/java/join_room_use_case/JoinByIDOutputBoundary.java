package join_room_use_case;

public interface JoinByIDOutputBoundary {
    void prepareRoomView();
    void prepareFailView(String error);

}
