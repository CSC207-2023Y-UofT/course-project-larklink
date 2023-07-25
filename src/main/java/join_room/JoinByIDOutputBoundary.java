package join_room;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(int roomID);
    void prepareFailView();

}
