package use_cases_and_adapters.join_room;

/**
 * An output boundary for join room use case.
 * This class is an abstraction layer between JoinRoomInteractor and JoinRoomPresenter.
 */
public interface JoinRoomOutputBoundary {

    /**
     * Displays RoomView of the room that user has entered.
     *
     * @param messageHistory a history of messages stored in the room
     */
    void prepareRoomView(String messageHistory);

    /**
     * Displays a fail view indicating that matching room is not found.
     */
    void prepareFailView();

}
