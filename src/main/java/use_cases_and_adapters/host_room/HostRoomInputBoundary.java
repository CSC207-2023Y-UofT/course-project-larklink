package use_cases_and_adapters.host_room;


/**
 * An input boundary for host room use case
 * This class is an abstraction layer between HostRoomController
 * and HostRoomInteractor
 */
public interface HostRoomInputBoundary {

    /**
     * Handles the host room use case
     * @param roomName the name to be given to the new room
     */
    void hostRoom(String roomName);
}
