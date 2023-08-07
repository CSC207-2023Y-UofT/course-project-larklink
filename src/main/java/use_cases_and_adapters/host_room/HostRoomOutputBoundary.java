package use_cases_and_adapters.host_room;

/**
 * An output boundary for host room use case.
 * This class is an abstraction layer between HostRoomInteractor and HostRoomPresenter.
 */
public interface HostRoomOutputBoundary {

    /**
     * Construct and display the room view for a room in the db
     * @param messageHistory messages previously sent in that room
     */
    void prepareRoomView(String messageHistory);

    /**
     * Construct and display when the host is already hosting another room
     * @param hostingRoom the room the host is already tied to
     */
    void prepareMultipleHostingView(String hostingRoom);

    /**
     * Construct and display this view when there already exists a room
     * with this name in the roomDB
     */
    void prepareDuplicateNameView();

}
