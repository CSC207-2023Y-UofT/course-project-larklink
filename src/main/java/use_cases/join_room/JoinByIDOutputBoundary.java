package use_cases.join_room;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareFailView();

}
