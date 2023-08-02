package use_cases_and_adapters.join_room;

import java.util.List;

/**
 * An input boundary for join room use case.
 * This class is an abstraction layer between JoinRoomController and JoinRoomInteractor.
 */

public interface JoinRoomInputBoundary {
    void handleJoinRoom(String roomName);
    List<String> loadRoomNames();
}
