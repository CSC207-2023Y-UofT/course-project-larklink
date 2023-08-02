package use_cases_and_adapters.join_room;

/**
 * An output boundary for join room use case.
 * This class is an abstraction layer between JoinRoomInteractor and JoinRoomPresenter.
 */
public interface JoinRoomOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareFailView();

}
