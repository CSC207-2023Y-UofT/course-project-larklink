package join_room;

/**
 * An input boundary for join room use case.
 */

public interface JoinRoomInputBoundary {
    void handleJoinRoom(String roomName);
}
