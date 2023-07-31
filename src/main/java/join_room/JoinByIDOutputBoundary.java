package join_room;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareFailView();

}
