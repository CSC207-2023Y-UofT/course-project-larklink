package use_cases_and_adapters.join_room;

import java.util.List;

/**
 * An input boundary for join room use case.
 * This class is an abstraction layer between JoinRoomController and JoinRoomInteractor.
 */

public interface JoinRoomInputBoundary {

    /**
     * Handles join room use case using given room name from outer layer.
     * If matching room is not found, it prepares a fail view indicating that matching room is not found.
     * If matching room is found, it prepares RoomView and user enters that room.
     *
     * @param roomName the name of room that user tries to join in
     */
    void handleJoinRoom(String roomName);

    /**
     * Retrieves a list of all room names of the existing rooms.
     *
     * @return a list of all room names
     */
    List<String> loadRoomNames();
}
