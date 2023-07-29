package join_room;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(Integer roomID);
    void prepareFailView();

}
