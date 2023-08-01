package use_cases_and_adapters.join_room;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareFailView();

}
