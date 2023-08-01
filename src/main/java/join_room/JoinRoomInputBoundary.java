package join_room;

import java.util.List;

/**
 * An input boundary for join room use case.
 */

public interface JoinRoomInputBoundary {
    void handleJoinRoom(String roomName);
    List<String> loadRoomNames();
}
